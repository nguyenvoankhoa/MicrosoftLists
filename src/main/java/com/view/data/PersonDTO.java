package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String name;
    private byte[] image;
    private ColumnType type;

    public PersonDTO(Person person) {
        this.name = person.getName();
        this.image = person.getImage();
        this.type = person.getType();
    }
}

