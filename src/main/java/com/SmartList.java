package com;

import com.column.factory.ColumnFactory;
import com.column.ColumnType;
import com.column.IColumn;
import com.column.datatype.IData;
import com.column.factory.DataFactory;
import com.permission.PermissionManagement;
import com.view.View;
import com.view.ViewType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@NoArgsConstructor
public class SmartList extends Template {
    private List<Form> forms;
    private List<Row> rows;

    private boolean isSave;
    private List<View> views;
    private PermissionManagement permissionManagement;

    public SmartList(String title, PermissionManagement pm) {
        super(title);
        this.forms = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.views = new ArrayList<>();
        this.permissionManagement = pm;
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

    public int getColumnIndexByName(String name) {
        IColumn c = getColumnByName(name);
        return getColumnIndex(c);
    }

    public IColumn createNewColumn(ColumnType type, String name) {
        IColumn c = getColumnByName(name);
        return Optional.ofNullable(c).orElseGet(() -> {
            ColumnFactory columnFactory = new ColumnFactory(name);
            IColumn<Object> column = columnFactory.getColumn(type);
            getColumns().add(column);
            for (Row row : rows) {
                DataFactory df = new DataFactory();
                row.getIDataList().add(df.createData(column.getType()));
            }
            return column;
        });
    }


    public int createNewRow() {
        Row newRow = new Row();
        this.getRows().add(newRow);
        List<IData> rowData = newRow.getIDataList();
        for (IColumn column : getColumns()) {
            DataFactory df = new DataFactory();
            rowData.add(df.createData(column.getType()));
        }
        return getRows().size() - 1;
    }

    public void addData(String name, int rId, Object data) {
        int cId = getColumnIndexByName(name);
        List<IData> iDataList = this.getRows().get(rId).getIDataList();
        iDataList.get(cId).setData(data);
    }

    public IColumn getColumnByName(String name) {
        return getColumns().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }


    public Object getData(String name, int rId) {
        int cId = getColumnIndexByName(name);
        return this.getRows().get(rId).getIDataList().get(cId).getData();
    }

    public void sort(IColumn<Object> column, boolean ascending) {
        int cId = getColumnIndex(column);

        Comparator<Row> rowComparator = (row1, row2) -> {
            IData<?> iData1 = row1.getIDataList().get(cId);
            IData<?> iData2 = row2.getIDataList().get(cId);
            Comparator<IData<?>> comparator = (Comparator<IData<?>>) iData1;
            return ascending ? comparator.compare(iData1, iData2) : comparator.compare(iData2, iData1);
        };

        getRows().sort(rowComparator);
    }


    public void sortDesc(String name) {
        IColumn column = getColumnByName(name);
        sort(column, false);
    }

    public void sortAsc(String name) {
        IColumn column = getColumnByName(name);
        sort(column, true);
    }

    public List<Object> getListFilter(String colName) {
        int cId = getColumnIndexByName(colName);
        return getRows().stream().map(l -> l.getIDataList().get(cId).getImportantData())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Row> filter(String colName, Object criteria) {
        int cId = getColumnIndexByName(colName);
        return getRows().stream()
                .filter(l -> l.getIDataList().get(cId).getImportantData().equals(criteria))
                .collect(Collectors.toList());
    }

    public Map<Object, List<Row>> groupBy(String colName) {
        int cId = getColumnIndexByName(colName);
        return rows.stream()
                .collect(Collectors.groupingBy(row -> row.getIDataList().get(cId).getImportantData()));
    }

    public boolean addRowData(IData... dList) {
        return Optional.of(dList.length)
                .filter(this::checkSize)
                .map(size -> {
                    int rId = createNewRow();
                    addDataMatchColumns(rId, dList);
                    return true;
                })
                .orElse(false);
    }

    private void addDataMatchColumns(int rId, IData[] dList) {
        int[] dataIndex = {0};
        getColumns().stream()
                .filter(column -> dataIndex[0] < dList.length)
                .filter(column -> column.getType().equals(dList[dataIndex[0]].getType()))
                .forEach(column -> {
                    addData(column.getName(), rId, dList[dataIndex[0]]);
                    dataIndex[0]++;
                });
    }

    public boolean checkSize(int size) {
        return size <= getColumns().size();
    }


    public void moveColumn(IColumn col, int direction) {
        int cId = getColumnIndex(col);
        int newIndex = cId + direction;
        IColumn adjacentCol = getColumns().get(newIndex);
        getColumns().set(newIndex, col);
        getColumns().set(cId, adjacentCol);
        for (Row row : getRows()) {
            List<IData> rowData = row.getIDataList();
            IData temp = rowData.get(cId);
            rowData.set(cId, rowData.get(newIndex));
            rowData.set(newIndex, temp);
        }
    }

    public void moveLeft(String colName) {
        IColumn col = getColumnByName(colName);
        moveColumn(col, -1);
    }

    public void moveRight(String colName) {
        IColumn col = getColumnByName(colName);
        moveColumn(col, 1);
    }

    public long count(String colName) {
        int cId = getColumnIndexByName(colName);
        return getRows().stream().filter(l -> l.getIDataList().get(cId) != null).count();
    }

    public boolean createView(ViewType viewType, String name) {
        return getViews().stream()
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .map(v -> false)
                .orElseGet(() -> {
                    views.add(new View(viewType, name));
                    return true;
                });
    }

    public void hideColumn(String colName) {
        IColumn column = getColumnByName(colName);
        column.setVisible(false);
    }

    public void showColumn(String colName) {
        IColumn column = getColumnByName(colName);
        column.setVisible(true);
    }

    public List<Row> getPage(int pageNumber, int pageSize) {
        int fromIndex = (pageNumber - 1) * pageSize;
        return rows.stream()
                .skip(Math.max(0, fromIndex))
                .limit(pageSize)
                .toList();
    }
}
