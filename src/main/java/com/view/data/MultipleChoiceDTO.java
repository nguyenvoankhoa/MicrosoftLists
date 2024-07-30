package com.view.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MultipleChoiceDTO {
    private List<ChoiceDTO> choices;
    private ColumnType type;
}
