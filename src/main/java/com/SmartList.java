package com;

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

    public SmartList(String title) {
        super(title);
        this.forms = new ArrayList<>();
        this.rows = new ArrayList<>();
    }


    public void createForm() {
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
            row.getData().add(df.createData(column.getType()));
        }
        return column;
    }


    public void createNewRow() {
        Row newRow = new Row();
        this.getRows().add(newRow);
        List<Data> rowData = newRow.getData();
        for (IColumn<Object> column : getColumns()) {
            DataFactory df = new DataFactory();
            Object data = df.createData(column.getType());
            rowData.add(df.createData(column.getType()));
        }
    }


    public void addData(IColumn<Object> column, int rId, Object data) {
        int cId = getColumnIndex(column);
        List<Data> dataList = this.getRows().get(rId).getData();
        dataList.get(cId).setData(data);
    }


    public Object getData(IColumn<Object> column, int rId) {
        int cId = getColumnIndex(column);
        return this.getRows().get(rId).getData().get(cId).getData();
    }
    public void sort(IColumn<Object> column, boolean ascending) {
        int cId = getColumnIndex(column);

        Comparator<Row> rowComparator = (row1, row2) -> {
            Data<?> data1 = row1.getData().get(cId);
            Data<?> data2 = row2.getData().get(cId);
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

    public void filter(IColumn<Object> column, String criteria) {
        getColumnIndex(column);
    }

    public Map<Object, List<Row>> groupBy(IColumn<Object> column) {
        int cId = getColumnIndex(column);
        return rows.stream()
                .collect(Collectors.groupingBy(row -> row.getData().get(cId).getData()));
    }
}
