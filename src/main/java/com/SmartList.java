package com;

import com.column.factory.ColumnFactory;
import com.column.ColumnType;
import com.column.IColumn;
import com.column.datatype.IData;
import com.column.factory.DataFactory;
import com.permission.PermissionManagement;
import com.view.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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


    public void createForm(List<IColumn> columns, String name) {
        Form form = new Form(this, columns, name);
        forms.add(form);
    }

    public IColumn createNewColumn(ColumnType type, String name) {
        IColumn c = Common.getColumnByName(this, name);
        return Optional.ofNullable(c).orElseGet(() -> {
            ColumnFactory columnFactory = new ColumnFactory(name);
            IColumn<Object> column = columnFactory.getColumn(type);
            getColumns().add(column);
            for (Row row : rows) {
                DataFactory df = new DataFactory();
                row.getIDataList().add(df.createData(column.getColumnType()));
            }
            return column;
        });
    }


    public void removeColumn(String name) {
        int cId = Common.getColumnIndexByName(this, name);
        getRows().forEach(row -> row.getIDataList().remove(cId));
        getColumns().remove(cId);
    }

    public int createNewRow() {
        Row newRow = new Row();
        this.getRows().add(newRow);
        List<IData> rowData = newRow.getIDataList();
        for (IColumn column : getColumns()) {
            DataFactory df = new DataFactory();
            rowData.add(df.createData(column.getColumnType()));
        }
        return getRows().size() - 1;
    }

    public void addData(String name, int rId, Object data) {
        IColumn<Object> column = Common.getColumnByName(this, name);
        int cId = Common.getColumnIndex(this, column);

        Optional.of(data)
                .filter(column::checkConstraint)
                .ifPresent(validData -> this.getRows().get(rId).addData(cId, validData));
    }

    public Object getData(String name, int rId) {
        int cId = Common.getColumnIndexByName(this, name);
        return this.getRows().get(rId).getData(cId);
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
                .filter(column -> column.getColumnType().equals(dList[dataIndex[0]].getType()))
                .forEach(column -> {
                    addData(column.getName(), rId, dList[dataIndex[0]]);
                    dataIndex[0]++;
                });
    }

    public boolean checkSize(int size) {
        return size <= getColumns().size();
    }


    public Row addRowData(Map<String, Object> mData) {
        int rId = createNewRow();
        for (Map.Entry<String, Object> entry : mData.entrySet()) {
            addData(entry.getKey(), rId, entry.getValue());
        }
        return getRows().get(rId);
    }


    public void moveColumn(IColumn col, int direction) {
        int cId = Common.getColumnIndex(this, col);
        int newIndex = cId + direction;
        List<IColumn> columns = getColumns();
        Collections.swap(columns, cId, newIndex);

        for (Row row : getRows()) {
            List<IData> rowData = row.getIDataList();
            Collections.swap(rowData, cId, newIndex);
        }
    }

    public void moveLeft(String colName) {
        IColumn col = Common.getColumnByName(this, colName);
        moveColumn(col, -1);
    }

    public void moveRight(String colName) {
        IColumn col = Common.getColumnByName(this, colName);
        moveColumn(col, 1);
    }

    public long count(String colName) {
        int cId = Common.getColumnIndexByName(this, colName);
        return getRows().stream().filter(l -> l.getIDataList().get(cId) != null).count();
    }


    public void hideColumn(String colName) {
        IColumn column = Common.getColumnByName(this, colName);
        column.setVisible(false);
    }

    public void showColumn(String colName) {
        IColumn column = Common.getColumnByName(this, colName);
        column.setVisible(true);
    }

    public List<View> createView(ViewType viewType, Object... params) {
        View v = new ViewFactory(this).createView(viewType, params);
        this.views.add(v);
        return this.views;
    }

}
