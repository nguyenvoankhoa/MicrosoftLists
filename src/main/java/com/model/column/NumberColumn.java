package com.model.column;

import com.model.datatype.Number;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class NumberColumn extends Column implements IColumn<Number> {
    private Number number;

    private double minVal = Double.MIN_VALUE;

    private double maxVal = Double.MAX_VALUE;

    public NumberColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public Number getDefaultData() {
        return number;
    }

    @Override
    public void setDefaultData(String str) {
        setNumber(Number.builder().num(Double.parseDouble(str)).build());
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        return Common.checkType(data.getClass(), Number.class);
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        String[] parts = data.split(";");
        return new Number(colName, parts);
    }

}
