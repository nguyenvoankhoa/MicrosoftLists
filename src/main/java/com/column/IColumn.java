package com.column;

import com.column.factory.DataFactory;

public interface IColumn<T> {
    String getName();

    void setName(String name);

    T getDefaultData();

    void setVisible(boolean data);

    boolean isVisible();

    ColumnType getType();
}
