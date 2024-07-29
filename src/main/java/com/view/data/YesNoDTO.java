package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.YesNo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoDTO {
    private boolean isChosen;
    private ColumnType type;

    public YesNoDTO(YesNo yesNo) {
        this.isChosen = yesNo.isChosen();
        this.type = yesNo.getType();
    }
}

