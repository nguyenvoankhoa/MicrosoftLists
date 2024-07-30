package com.dto.column;

import com.model.column.ColumnType;
import com.dto.data.ChoiceDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChoiceColumnDTO extends BaseColumDTO{
    private List<ChoiceDTO> choices;
    private boolean isMultiSelect;
    private ColumnType type;
}
