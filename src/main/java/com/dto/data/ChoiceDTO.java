package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceDTO {
    private String name;
    private String shape;
    private String color;
    private ColumnType type;

}

