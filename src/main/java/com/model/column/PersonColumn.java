package com.model.column;

import com.model.datatype.MultiplePerson;
import com.model.datatype.Person;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonColumn extends Column implements IColumn<List<Person>> {

    public PersonColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public List<Person> getDefaultData() {
        return Collections.emptyList();
    }

    @Override
    public void setDefaultData(String str) {
        //
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        return getColumnType() == ColumnType.PERSON
                && Common.checkType(data.getClass(), Person.class);
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        return getColumnType() == ColumnType.MULTIPLE_PERSON ?
                new MultiplePerson(colName, createSimpleData(data))
                : Person.builder().name(data).build();
    }

    public List<Person> createSimpleData(String data) {
        return Arrays.stream(data.split(";"))
                .map(d -> Person.builder().name(d).build())
                .collect(Collectors.toList());
    }

}
