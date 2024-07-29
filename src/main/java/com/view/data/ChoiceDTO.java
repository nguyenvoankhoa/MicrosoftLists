package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.Choice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceDTO {
    private String name;
    private String shape;
    private String color;
    private ColumnType type;

    public ChoiceDTO(Choice choice) {
        this.name = choice.getName();
        this.shape = choice.getShape();
        this.color = choice.getColor();
        this.type = choice.getType();
    }
}

