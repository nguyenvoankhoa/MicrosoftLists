package com.view.column;

import com.model.column.ColumnType;
import com.view.data.PersonDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonColumnDTO extends BaseColumDTO{
    private List<PersonDTO> people;
    private boolean isMultiSelect;
    private ColumnType type;

}
