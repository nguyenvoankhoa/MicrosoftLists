package com.service;

import com.model.Form;
import com.model.Row;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.datatype.IData;
import com.service.factory.ColumnFactory;
import com.service.factory.DataFactory;
import com.model.view.View;
import com.model.view.ViewType;

import java.util.*;

public class SmartListService {

    private SmartList sl;

    public SmartListService(SmartList sl) {
        this.sl = sl;
    }

    public void createForm(List<IColumn<?>> columns, String name) {
        Form form = new Form(sl, columns, name);
        sl.getForms().add(form);
    }

    public IColumn createNewColumn(ColumnType type, String name) {
        IColumn c = Common.getColumnByName(sl, name);
        return Optional.ofNullable(c).orElseGet(() -> {
            ColumnFactory columnFactory = new ColumnFactory(name);
            IColumn<?> column = columnFactory.getColumn(type);
            sl.getColumns().add(column);
            for (Row row : sl.getRows()) {
                DataFactory df = new DataFactory();
                row.getIDataList().add(df.createData(column.getColumnType()));
            }
            return column;
        });
    }


    public void removeColumn(String name) {
        int cId = Common.getColumnIndexByName(sl, name);
        sl.getRows().forEach(row -> row.getIDataList().remove(cId));
        sl.getColumns().remove(cId);
    }

    public int createNewRow() {
        Row newRow = new Row();
        sl.getRows().add(newRow);
        List<IData> rowData = newRow.getIDataList();
        for (IColumn<?> column : sl.getColumns()) {
            DataFactory df = new DataFactory();
            rowData.add(df.createData(column.getColumnType()));
        }
        return sl.getRows().size() - 1;
    }

    public void addData(String name, int rId, Object data) {
        IColumn<?> column = Common.getColumnByName(sl, name);
        int cId = Common.getColumnIndex(sl, column);
        if (checkSameType(column, data)) {
            sl.getRows().get(rId).addData(cId, data);
        } else {
            sl.getRows().get(rId).addData(cId, convertToList(data));
        }
    }

    public boolean checkSameType(IColumn a, Object b) {
        return (a.getDefaultData() instanceof List && b instanceof List)
                || !(a.getDefaultData() instanceof List && !(b instanceof List));
    }

    public List<?> convertToList(Object data) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(data);
        return list;
    }


    public Object getData(String name, int rId) {
        int cId = Common.getColumnIndexByName(sl, name);
        return sl.getRows().get(rId).getData(cId);
    }


    public boolean addRowData(IData<?>... dList) {
        return Optional.of(dList.length)
                .filter(this::checkSize)
                .map(size -> {
                    int rId = createNewRow();
                    addDataMatchColumns(rId, dList);
                    return true;
                })
                .orElse(false);
    }

    private void addDataMatchColumns(int rId, IData<?>[] dList) {
        int[] dataIndex = {0};
        sl.getColumns().stream()
                .filter(column -> dataIndex[0] < dList.length)
                .filter(column -> column.getColumnType().equals(dList[dataIndex[0]].getType()))
                .forEach(column -> {
                    addData(column.getName(), rId, dList[dataIndex[0]]);
                    dataIndex[0]++;
                });
    }

    public boolean checkSize(int size) {
        return size <= sl.getColumns().size();
    }


    public Row addRowData(Map<String, Object> mData) {
        int rId = createNewRow();
        for (Map.Entry<String, Object> entry : mData.entrySet()) {
            addData(entry.getKey(), rId, entry.getValue());
        }
        return sl.getRows().get(rId);
    }


    public void moveColumn(IColumn<?> col, int direction) {
        int cId = Common.getColumnIndex(sl, col);
        int newIndex = cId + direction;
        List<IColumn> columns = sl.getColumns();
        Collections.swap(columns, cId, newIndex);

        for (Row row : sl.getRows()) {
            List<IData> rowData = row.getIDataList();
            Collections.swap(rowData, cId, newIndex);
        }
    }

    public void moveLeft(String colName) {
        IColumn<?> col = Common.getColumnByName(sl, colName);
        moveColumn(col, -1);
    }

    public void moveRight(String colName) {
        IColumn<?> col = Common.getColumnByName(sl, colName);
        moveColumn(col, 1);
    }

    public long count(String colName) {
        int cId = Common.getColumnIndexByName(sl, colName);
        return sl.getRows().stream().filter(l -> l.getIDataList().get(cId) != null).count();
    }


    public void hideColumn(String colName) {
        IColumn<?> column = Common.getColumnByName(sl, colName);
        column.setVisible(false);
    }

    public void showColumn(String colName) {
        IColumn<?> column = Common.getColumnByName(sl, colName);
        column.setVisible(true);
    }

    public List<View> createView(ViewType viewType, Object... params) {
        View v = new ViewFactory(sl).createView(viewType, params);
        sl.getViews().add(v);
        return sl.getViews();
    }
}
