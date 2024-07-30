package com.view.column;

import com.model.column.ColumnType;
import com.model.datatype.Number;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumnDTO extends BaseColumDTO{
    private Number number;
    private ColumnType type;
}

