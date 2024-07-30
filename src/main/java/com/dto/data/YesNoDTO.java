package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoDTO {
    private boolean isChosen;
    private ColumnType type;
}

