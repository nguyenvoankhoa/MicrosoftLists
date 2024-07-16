package com.column.factory;

import com.column.datatype.Data;
import com.column.datatype.Number;

public class NumberFactory implements DataFactory<Number> {
    @Override
    public Data<Number> createData() {
        return new Number();
    }
}
