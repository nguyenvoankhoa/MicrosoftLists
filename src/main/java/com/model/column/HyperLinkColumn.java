package com.model.column;

import com.model.datatype.HyperLink;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

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
    public void checkConstraint(Object data) {
        Predicate<HyperLink> requirePredicate = d -> !isRequire() || d != null;
        Common.checkValid(requirePredicate.test((HyperLink) data));
    }

    @Override
    public HyperLink createSimpleData(Object data) {
        return new HyperLink((String) data);
    }
}
