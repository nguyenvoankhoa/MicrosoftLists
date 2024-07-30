package com.dto.column;

import com.model.column.ColumnType;
import com.model.datatype.YesNo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumnDTO extends BaseColumDTO {
    private YesNo data;
    private ColumnType type;
}
