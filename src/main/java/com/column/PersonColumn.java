package com.column;

import com.column.datatype.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonColumn extends Column implements IColumn<Person> {
    private Person person;

    @Override
    public Person getDefaultData() {
        return person;
    }

    @Override
    public ColumnType getType() {
        return ColumnType.PERSON;
    }
}
