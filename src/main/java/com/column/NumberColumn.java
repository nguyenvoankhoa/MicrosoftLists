package com.column;

import com.column.factory.DataFactory;
import com.column.factory.NumberFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumn implements IColumn<Number> {
    private String name;
    private static final ColumnType type = ColumnType.NUMBER;
    private Number number;

    @Override
    public Number getData() {
        return this.number;
    }

    @Override
    public void setData(Number data) {
        this.number = data;
    }

    @Override
    public DataFactory<?> getDataFactory() {
        return new NumberFactory();
    }
}
