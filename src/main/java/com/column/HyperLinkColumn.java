package com.column;

import com.column.datatype.HyperLink;
import lombok.Getter;
import lombok.Setter;

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
}
