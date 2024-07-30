package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.HyperLink;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HyperLinkDTO {
    private String link;
    private ColumnType type;
}
