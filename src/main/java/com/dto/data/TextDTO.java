package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextDTO  extends  BaseDataDTO{
    private String str;
    private ColumnType type;
}
