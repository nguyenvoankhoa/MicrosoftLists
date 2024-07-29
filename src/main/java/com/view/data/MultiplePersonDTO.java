package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.MultiplePerson;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MultiplePersonDTO {
    private List<PersonDTO> people;
    private ColumnType type;

    public MultiplePersonDTO(MultiplePerson multiplePerson) {
        this.people = multiplePerson.getData().stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
        this.type = multiplePerson.getType();
    }
}

