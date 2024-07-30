package com.dto.column;

import com.model.column.ColumnType;
import com.model.datatype.HyperLink;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HyperLinkColumnDTO extends BaseColumDTO {
    private HyperLink hyperLink;
    private ColumnType type;

}

