package com.column;

import com.column.factory.DataFactory;

public interface IColumn<T> {
    String getName();

    void setName(String name);

    T getData();

    void setData(T data);

    DataFactory<?> getDataFactory();
}
