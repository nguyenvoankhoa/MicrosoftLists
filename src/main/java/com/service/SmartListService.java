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
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@NoArgsConstructor
@Component
public class SmartListService {

    public void createForm(SmartList sl, List<IColumn<?>> columns, String name) {
        Form form = new Form(sl, columns, name);
        sl.getForms().add(form);
    }

    public IColumn createNewColumn(SmartList sl, ColumnType type, String name) {
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


    public void removeColumn(SmartList sl, String name) {
        int cId = Common.getColumnIndexByName(sl, name);
        sl.getRows().forEach(row -> row.getIDataList().remove(cId));
        sl.getColumns().remove(cId);
    }

    public int createNewRow(SmartList sl) {
        Row newRow = new Row();
        sl.getRows().add(newRow);
        List<IData> rowData = newRow.getIDataList();
        for (IColumn<?> column : sl.getColumns()) {
            DataFactory df = new DataFactory();
            rowData.add(df.createData(column.getColumnType()));
        }
        return sl.getRows().size() - 1;
    }

    public void addData(SmartList sl, String name, int rId, Object data) {
        IColumn<?> column = Common.getColumnByName(sl, name);
        int cId = Common.getColumnIndex(sl, column);
        if (checkSameType(column, data)) {
            sl.getRows().get(rId).addData(cId, data);
        } else {
            sl.getRows().get(rId).addData(cId, convertToList(data));
        }
    }

    public boolean checkSameType(IColumn<?> a, Object b) {
        boolean bothAreLists = a.getDefaultData() instanceof List && b instanceof List;
        boolean bothAreNotList = !(a.getDefaultData() instanceof List) && !(b instanceof List);
        return bothAreLists || bothAreNotList;
    }


    public List<?> convertToList(Object data) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(data);
        return list;
    }


    public Object getData(SmartList sl, String name, int rId) {
        int cId = Common.getColumnIndexByName(sl, name);
        return sl.getRows().get(rId).getData(cId);
    }


    public boolean addRowData(SmartList sl, IData<?>... dList) {
        if (checkSize(sl, dList.length)) {
            int rId = createNewRow(sl);
            addDataMatchColumns(sl, rId, dList);
            return true;
        }
        return false;
    }

    private void addDataMatchColumns(SmartList sl, int rId, IData<?>[] dList) {
        int[] dataIndex = {0};
        sl.getColumns().stream()
                .filter(column -> dataIndex[0] < dList.length)
                .filter(column -> column.getColumnType().equals(dList[dataIndex[0]].getType()))
                .forEach(column -> {
                    addData(sl, column.getName(), rId, dList[dataIndex[0]]);
                    dataIndex[0]++;
                });
    }

    public boolean checkSize(SmartList sl, int size) {
        return size <= sl.getColumns().size();
    }


    public Row addRowData(SmartList sl, Map<String, Object> mData) {
        int rId = createNewRow(sl);
        for (Map.Entry<String, Object> entry : mData.entrySet()) {
            addData(sl, entry.getKey(), rId, entry.getValue());
        }
        return sl.getRows().get(rId);
    }


    public SmartList moveColumn(SmartList sl, IColumn<?> col, int direction) {
        int cId = Common.getColumnIndex(sl, col);
        int newIndex = cId + direction;
        List<IColumn> columns = sl.getColumns();
        Collections.swap(columns, cId, newIndex);

        for (Row row : sl.getRows()) {
            List<IData> rowData = row.getIDataList();
            Collections.swap(rowData, cId, newIndex);
        }
        return sl;
    }

    public SmartList moveLeft(SmartList sl, String colName) {
        IColumn<?> col = Common.getColumnByName(sl, colName);
        return moveColumn(sl, col, -1);
    }

    public SmartList moveRight(SmartList sl, String colName) {
        IColumn<?> col = Common.getColumnByName(sl, colName);
        return moveColumn(sl, col, 1);
    }


    public void hideColumn(SmartList sl, String colName) {
        IColumn<?> column = Common.getColumnByName(sl, colName);
        column.setVisible(false);
    }

    public void showColumn(SmartList sl, String colName) {
        IColumn<?> column = Common.getColumnByName(sl, colName);
        column.setVisible(true);
    }

    public View createView(SmartList sl, ViewType viewType, Object... params) {
        View v = new ViewFactory(sl).createView(viewType, params);
        sl.getViews().add(v);
        return v;
    }

}
