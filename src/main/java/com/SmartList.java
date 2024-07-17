package com;

import com.column.Column;
import com.column.datatype.Person;
import com.column.factory.ColumnFactory;
import com.column.ColumnType;
import com.column.IColumn;
import com.column.datatype.Data;
import com.column.factory.DataFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@NoArgsConstructor
public class SmartList extends Template {
    private List<Form> forms;
    private String id;
    private List<Row> rows;
    private boolean isSave;
    private List<View> views;

    private IPermissionManagement permissionManagement;

    public SmartList(String title) {
        super(title);
        this.forms = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.views = new ArrayList<>();
        this.permissionManagement = new PermissionManagement();
    }


    public void createForm(List<IColumn> columns) {
        Form form = new Form();
        for (IColumn col : columns) {
            form.getColumns().add(col);
        }
        forms.add(new Form());
    }

    public int getColumnIndex(IColumn<Object> column) {
        return IntStream.range(0, getColumns().size())
                .filter(i -> getColumns().get(i).equals(column))
                .findFirst()
                .orElse(-1);
    }

    public IColumn<Object> createNewColumn(ColumnType type) {
        ColumnFactory columnFactory = new ColumnFactory();
        IColumn<Object> column = columnFactory.getColumn(type);
        getColumns().add(column);
        for (Row row : rows) {
            DataFactory df = new DataFactory();
            row.getDataList().add(df.createData(column.getType()));
        }
        return column;
    }


    public void createNewRow() {
        Row newRow = new Row();
        this.getRows().add(newRow);
        List<Data> rowData = newRow.getDataList();
        for (IColumn<Object> column : getColumns()) {
            DataFactory df = new DataFactory();
            rowData.add(df.createData(column.getType()));
        }
    }


    public void addData(IColumn<Object> column, int rId, Object data) {
        int cId = getColumnIndex(column);
        List<Data> dataList = this.getRows().get(rId).getDataList();
        dataList.get(cId).setData(data);
    }


    public Object getData(IColumn<Object> column, int rId) {
        int cId = getColumnIndex(column);
        return this.getRows().get(rId).getDataList().get(cId).getData();
    }

    public void sort(IColumn<Object> column, boolean ascending) {
        int cId = getColumnIndex(column);

        Comparator<Row> rowComparator = (row1, row2) -> {
            Data<?> data1 = row1.getDataList().get(cId);
            Data<?> data2 = row2.getDataList().get(cId);
            Comparator<Data<?>> comparator = (Comparator<Data<?>>) data1;
            return ascending ? comparator.compare(data1, data2) : comparator.compare(data2, data1);
        };

        getRows().sort(rowComparator);
    }


    public void sortDesc(IColumn<Object> column) {
        sort(column, false);
    }

    public void sortAsc(IColumn<Object> column) {
        sort(column, true);
    }

    public List<Object> getListFilter(IColumn<Object> column) {
        int cId = getColumnIndex(column);
        return getRows().stream().map(l -> l.getDataList().get(cId).getImportantData())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Object> filter(IColumn<Object> column, Object criteria) {
        int cId = getColumnIndex(column);
        return getRows().stream()
                .filter(l -> l.getDataList().get(cId).getImportantData().equals(criteria))
                .collect(Collectors.toList());
    }

    public Map<Object, List<Row>> groupBy(IColumn<Object> column) {
        int cId = getColumnIndex(column);
        return rows.stream()
                .collect(Collectors.groupingBy(row -> row.getDataList().get(cId).getImportantData()));
    }

    public void moveColumn(IColumn col, int direction) {
        int cId = getColumnIndex(col);
        int newIndex = cId + direction;
        IColumn adjacentCol = getColumns().get(newIndex);
        getColumns().set(newIndex, col);
        getColumns().set(cId, adjacentCol);
        for (Row row : getRows()) {
            List<Data> rowData = row.getDataList();
            Data temp = rowData.get(cId);
            rowData.set(cId, rowData.get(newIndex));
            rowData.set(newIndex, temp);
        }
    }

    public void moveLeft(IColumn col) {
        moveColumn(col, -1);
    }

    public void moveRight(IColumn col) {
        moveColumn(col, 1);
    }

    public long count(IColumn col) {
        int cId = getColumnIndex(col);
        return getRows().stream().filter(l -> l.getDataList().get(cId) != null).count();
    }

    public boolean createView(ViewType viewType, String title) {
        return getViews().stream()
                .filter(v -> v.getTitle().equals(title))
                .findFirst()
                .map(v -> false)
                .orElseGet(() -> {
                    views.add(new View(viewType, title));
                    return true;
                });
    }

}
