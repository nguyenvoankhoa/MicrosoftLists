package com.view.column;

import com.model.column.ColumnType;
import com.model.column.NumberColumn;
import com.model.datatype.Number;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumnDTO {
    private Number number;
    private double minVal;
    private double maxVal;
    private ColumnType type;

    public NumberColumnDTO(NumberColumn column) {
        this.number = column.getDefaultData();
        this.minVal = column.getMinVal();
        this.maxVal = column.getMaxVal();
        this.type = column.getColumnType();
    }
}

