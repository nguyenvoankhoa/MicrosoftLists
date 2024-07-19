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


    public void createForm(List<IColumn> columns) {
        Form form = new Form();
        for (IColumn col : columns) {
            form.getColumns().add(col);
        }
        forms.add(new Form());
    }


    public IColumn createNewColumn(ColumnType type, String name) {
        IColumn c = Common.getColumnByName(this, name);
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
            rowData.add(df.createData(column.getType()));
        }
        return getRows().size() - 1;
    }

    public void addData(String name, int rId, Object data) {
        int cId = Common.getColumnIndexByName(this, name);
        List<IData> iDataList = this.getRows().get(rId).getIDataList();
        iDataList.get(cId).setData(data);
    }


    public Object getData(String name, int rId) {
        int cId = Common.getColumnIndexByName(this, name);
        return this.getRows().get(rId).getIDataList().get(cId).getData();
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
        int cId = Common.getColumnIndex(this, col);
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

    public List<View> createBoardView(ViewType viewType, Object... params) {
        View v = new ViewFactory(this).createView(viewType, params);
        this.views.add(v);
        return this.views;
    }

}
