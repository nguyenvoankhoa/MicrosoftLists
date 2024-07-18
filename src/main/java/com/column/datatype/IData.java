package com.column.datatype;

import com.column.ColumnType;

public interface IData<T> {
    T getData();

    void setData(T data);

    Object getImportantData();

    ColumnType getType();
}
