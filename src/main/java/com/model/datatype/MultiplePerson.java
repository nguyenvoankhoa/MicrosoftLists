package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MultiplePerson implements IData<List<Person>> {
    List<Person> people;

    private ColumnType type = ColumnType.PEOPLE;

    public MultiplePerson() {
        this.people = new ArrayList<>();
    }

    @Override
    public List<Person> getData() {
        return this.people;
    }

    @Override
    public void setData(List<Person> data) {
        setPeople(data);
    }


    @Override
    public Object getImportantData() {
        return people;
    }


}
