package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.MultipleChoice;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MultipleChoiceDTO {
    private List<ChoiceDTO> choices;
    private ColumnType type;

    public MultipleChoiceDTO(MultipleChoice multipleChoice) {
        this.choices = multipleChoice.getData().stream()
                .map(ChoiceDTO::new)
                .toList();
        this.type = multipleChoice.getType();
    }
}
