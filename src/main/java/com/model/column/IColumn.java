package com.model.column;

public interface IColumn<T> {
    String getName();

    void setName(String name);

    T getDefaultData();

    void setVisible(boolean data);

    boolean isVisible();

    ColumnType getColumnType();

    void checkConstraint(Object data);

    T createSimpleData(Object data);
}
