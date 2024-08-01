package com.dto.data;
import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberDTO  extends  BaseDataDTO{
    private double num;
    private byte[] icon;
    private ColumnType type;
}

