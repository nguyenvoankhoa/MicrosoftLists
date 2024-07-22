package com.column;

import com.column.datatype.HyperLink;

import java.util.function.Predicate;

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

    @Override
    public boolean checkConstraint(Object data) {
        Predicate<Object> requirePredicate = d -> !isRequire() || d != null;
        return requirePredicate.test(data);
    }
}
