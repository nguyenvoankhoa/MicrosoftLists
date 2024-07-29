package com.view.column;

import com.model.column.ColumnType;
import com.model.column.YesNoColumn;
import com.model.datatype.YesNo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumnDTO {
    private YesNo data;
    private ColumnType type;

    public YesNoColumnDTO(YesNoColumn column) {
        this.data = column.getDefaultData();
        this.type = column.getColumnType();
    }
}
