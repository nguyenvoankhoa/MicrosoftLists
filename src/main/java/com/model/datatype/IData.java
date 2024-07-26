package com.model.datatype;

import com.model.column.ColumnType;

public interface IData<T> {
    T getData();

    void setData(T data);

    Object getImportantData();

    ColumnType getType();
}
