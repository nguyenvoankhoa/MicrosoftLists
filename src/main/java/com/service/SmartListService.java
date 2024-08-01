package com.service;

import com.exception.ConstraintViolationException;
import com.factory.ViewFactory;
import com.model.Form;
import com.model.Row;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.datatype.IData;
import com.factory.ColumnFactory;
import com.factory.DataFactory;
import com.model.view.View;
import com.model.view.ViewType;
import com.util.Common;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@NoArgsConstructor
@Component
public class SmartListService {

    public SmartList createForm(SmartList sl, List<IColumn<?>> columns, String name) {
        Form form = new Form(sl, columns, name);
        sl.getForms().add(form);
        return sl;
    }


    public IColumn createNewColumn(SmartList sl, ColumnType type, String name) {
        IColumn c = Common.getColumnByName(sl, name);
        Common.checkExist(c);
        ColumnFactory columnFactory = new ColumnFactory(name, type);
        IColumn<?> column = columnFactory.getColumn(type);
        sl.getColumns().add(column);
        for (Row row : sl.getRows()) {
            DataFactory df = new DataFactory(column.getName());
            row.getIDataList().add(df.createData(column.getColumnType()));
        }
        return column;
    }


    public SmartList removeColumn(SmartList sl, String name) {
        int cId = Common.getColumnIndexByName(sl, name);
        sl.getRows().forEach(row -> row.getIDataList().remove(cId));
        sl.getColumns().remove(cId);
        return sl;
    }

    public int createNewRow(SmartList sl) {
        Row newRow = new Row();
        sl.getRows().add(newRow);
        List<IData> rowData = newRow.getIDataList();
        for (IColumn<?> column : sl.getColumns()) {
            DataFactory df = new DataFactory(column.getName());
            rowData.add(df.createData(column.getColumnType()));
        }
        return sl.getRows().size() - 1;
    }

    public SmartList addData(SmartList sl, int rId, Object data, IColumn<?> column) {
        int cId = Common.getColumnIndex(sl, column);
        sl.getRows().get(rId).addData(cId, data);
        return sl;
    }

    public SmartList addCellData(SmartList sl, String name, int rId, String data) {
        IColumn<?> column = Common.getColumnByName(sl, name);
        Common.checkNonExist(column);
        Object value = column.handleCreateData(data, name);
        column.checkConstraint(value);
        return addData(sl, rId, value, column);
    }

    public Object getData(SmartList sl, String name, int rId) {
        int cId = Common.getColumnIndexByName(sl, name);
        return sl.getRows().get(rId).getData(cId);
    }


    public SmartList addRowData(SmartList sl, Map<String, String> mData) {
        int rId = createNewRow(sl);
        for (Map.Entry<String, String> entry : mData.entrySet()) {
            addCellData(sl, entry.getKey(), rId, entry.getValue());
        }
        return sl;
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


    public Row getRow(SmartList sl, int rowId) {
        if (rowId >= sl.getRows().size()) {
            throw new ConstraintViolationException();
        }
        return sl.getRows().get(rowId);
    }

    public void deleteRow(SmartList sl, int rowId) {
        sl.getRows().remove(rowId);
    }
}
