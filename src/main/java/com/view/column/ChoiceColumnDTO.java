package com.view.column;

import com.model.column.ChoiceColumn;
import com.model.column.ColumnType;
import com.view.data.ChoiceDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChoiceColumnDTO {
    private String name;
    private List<ChoiceDTO> choices;
    private boolean isMultiSelect;
    private ColumnType type;

    public ChoiceColumnDTO(ChoiceColumn choiceColumn) {
        this.name = choiceColumn.getName();
        this.choices = choiceColumn.getChoices().stream()
                .map(ChoiceDTO::new)
                .toList();
        this.isMultiSelect = choiceColumn.isMultiSelect();
        this.type = choiceColumn.getType();
    }
}
