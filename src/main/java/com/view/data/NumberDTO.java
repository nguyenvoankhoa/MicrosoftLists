package com.view.data;
import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberDTO {
    private double num;
    private byte[] icon;
    private ColumnType type;
}

