package com.column;

import com.column.datatype.HyperLink;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HyperLinkColumn extends Column implements IColumn {
    private HyperLink hyperLink;

    @Override
    public Object getDefaultData() {
        return hyperLink;
    }

    @Override
    public ColumnType getType() {
        return ColumnType.HYPERLINK;
    }
}
