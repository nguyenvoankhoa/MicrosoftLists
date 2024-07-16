package com.column.factory;

import com.column.datatype.Data;

public interface DataFactory<T> {
    Data<T> createData();
}
