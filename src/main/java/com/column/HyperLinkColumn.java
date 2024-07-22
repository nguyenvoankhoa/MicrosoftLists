package com.column;

import com.column.datatype.Choice;
import com.column.datatype.HyperLink;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class HyperLinkColumn extends Column implements IColumn<HyperLink> {
    private HyperLink hyperLink;

    public HyperLinkColumn(String name) {
        super(name);
        setType(ColumnType.HYPERLINK);
    }

    @Override
    public HyperLink getDefaultData() {
        return hyperLink;
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(HyperLink data) {
        Predicate<HyperLink> requirePredicate = d -> !isRequire() || d != null;
        return requirePredicate.test(data);
    }
}
