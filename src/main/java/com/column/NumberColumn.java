package com.column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumn extends Column implements IColumn<Number> {
    private Number number;

    public NumberColumn(String name) {
        super(name);
        setType(ColumnType.NUMBER);
    }

    @Override
    public Number getDefaultData() {
        return number;
    }
}
