package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MultipleChoiceDTO extends BaseDataDTO{
    private List<ChoiceDTO> choices;
    private ColumnType type;
}
