package com.view.column;

import com.model.column.ColumnType;
import com.model.column.PersonColumn;
import com.view.data.PersonDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonColumnDTO {
    private List<PersonDTO> people;
    private boolean isMultiSelect;
    private ColumnType type;

    public PersonColumnDTO(PersonColumn column) {
        this.people = column.getPeople().stream()
                .map(PersonDTO::new)
                .toList();
        this.isMultiSelect = column.isMultiSelect();
        this.type = column.getColumnType();
    }
}
