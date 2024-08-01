package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceDTO extends BaseDataDTO{
    private String name;
    private ColumnType type;
}

