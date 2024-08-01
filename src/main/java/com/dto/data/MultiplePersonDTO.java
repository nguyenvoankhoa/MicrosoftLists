package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiplePersonDTO extends BaseDataDTO{
    private List<PersonDTO> people;
    private ColumnType type;
}

