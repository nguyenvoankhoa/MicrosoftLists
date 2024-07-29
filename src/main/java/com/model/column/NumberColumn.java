package com.model.column;

import com.model.datatype.Number;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

@Getter
@Setter
public class NumberColumn extends Column implements IColumn<Number> {
    private Number number;

    private double minVal = Double.MIN_VALUE;

    private double maxVal = Double.MAX_VALUE;

    public NumberColumn(String name) {
        super(name);
        setType(ColumnType.NUMBER);
    }

    @Override
    public Number getDefaultData() {
        return number;
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Number data) {
        Predicate<Number> requirePredicate = d -> !isRequire() || d != null;
        Predicate<Number> minValPredicate = d -> minVal == Double.MIN_VALUE || d.getNum() >= getMinVal();
        Predicate<Number> maxValPredicate = d -> maxVal == Double.MAX_VALUE || d.getNum() <= getMaxVal();
        return requirePredicate.and(minValPredicate).and(maxValPredicate).test(data);
    }

    @Override
    public Number createSimpleData(Object data) {
        return new Number((Double) data);
    }


}
