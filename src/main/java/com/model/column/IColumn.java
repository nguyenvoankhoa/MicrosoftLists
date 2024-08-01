package com.model.column;

public interface IColumn<T> {
    String getName();

    void setName(String name);

    T getDefaultData();

    void setDefaultData(String str);

    void setVisible(boolean data);

    boolean isVisible();

    ColumnType getColumnType();

    boolean checkConstraint(Object data);

    Object handleCreateData(String data, String colName);
}
