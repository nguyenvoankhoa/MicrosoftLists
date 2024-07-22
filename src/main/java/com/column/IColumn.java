package com.column;

public interface IColumn<T> {
    String getName();

    void setName(String name);

    T getDefaultData();

    void setVisible(boolean data);

    boolean isVisible();

    ColumnType getColumnType();

    boolean checkConstraint(T data);
}
