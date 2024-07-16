package com.column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumn extends Column implements IColumn<Number> {
    private Number number;

    @Override
    public Number getDefaultData() {
        return number;
    }
    @Override
    public ColumnType getType() {
        return ColumnType.NUMBER;
    }
}
