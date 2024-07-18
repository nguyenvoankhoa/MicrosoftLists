package com.column;

public class LookupColumn extends Column implements IColumn {

    public LookupColumn(String name) {
        super(name);
    }

    @Override
    public Object getDefaultData() {
        return null;
    }

    @Override
    public ColumnType getType() {
        return null;
    }
}
