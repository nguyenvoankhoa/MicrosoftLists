package com;

import com.column.IColumn;
import com.column.TextColumn;
import com.column.datatype.Data;
import com.column.factory.DataFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Setter
@NoArgsConstructor
public class SmartList extends Template {
    private String title;
    private List<Form> forms;
    private String id;
    private List<IColumn> columns;

    private Map<IColumn, DataFactory<?>> columnFactories;
    private List<Row> rows;
    private boolean isSave;

    public SmartList(String title) {
        this.title = title;
        this.forms = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.columnFactories = new HashMap<>();
    }

    public void createForm() {
        forms.add(new Form());
    }

    public int getColumnIndex(IColumn<?> column) {
        return IntStream.range(0, getColumns().size())
                .filter(i -> getColumns().get(i).equals(column))
                .findFirst()
                .orElse(-1);
    }

    public void createNewColumn(IColumn column) {
        columns.add(column);
        columnFactories.put(column, column.getDataFactory());
        DataFactory<?> factory = column.getDataFactory();
        for (Row row : rows) {
            row.getData().add(factory.createData());
        }
    }


    public void createNewRow() {
        Row newRow = new Row();
        this.getRows().add(newRow);
        List<Data> rowData = newRow.getData();
        for (IColumn column : columns) {
            DataFactory<?> factory = columnFactories.get(column);
            rowData.add(factory.createData());
        }
    }


    public void addData(IColumn column, int rId, Object data){
        int cId = getColumnIndex(column);
        List<Data> dataList = this.getRows().get(rId).getData();
        dataList.get(cId).setData(data);
    }


    public Object getData(IColumn column, int rId) {
        int cId = getColumnIndex(column);
        return this.getRows().get(rId).getData().get(cId).getData();
    }

    public void sort(IColumn column) {
        getColumnIndex(column);
    }

    public void filter(IColumn column) {
        getColumnIndex(column);
    }

    public void groupBy(IColumn column) {
        getColumnIndex(column);
    }
}
