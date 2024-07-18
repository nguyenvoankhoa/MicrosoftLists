package com.column;

import com.column.datatype.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonColumn extends Column implements IColumn<Person> {
    private Person person;

    public PersonColumn(String name) {
        super(name);
        setType(ColumnType.PERSON);
    }

    @Override
    public Person getDefaultData() {
        return person;
    }
}
