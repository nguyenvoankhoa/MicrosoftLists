package com.view.column;

import com.model.column.ColumnType;
import com.model.column.HyperLinkColumn;
import com.model.datatype.HyperLink;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HyperLinkColumnDTO {
    private HyperLink hyperLink;
    private ColumnType type;

    public HyperLinkColumnDTO(HyperLinkColumn column) {
        this.hyperLink = column.getDefaultData();
        this.type = column.getColumnType();
    }
}

