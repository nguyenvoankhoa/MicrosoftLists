package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MultiplePerson extends BaseData implements IData<MultiplePerson> {
    private List<Person> people;

    private final ColumnType type = ColumnType.MULTIPLE_PERSON;

    public MultiplePerson(String colName) {
        super(colName);
        this.people = new ArrayList<>();
    }

    public MultiplePerson(String colName, List<Person> people) {
        super(colName);
        this.people = people;
    }

    @Override
    public MultiplePerson getData() {
        return this;
    }

    @Override
    public void setData(MultiplePerson data) {
        setPeople(data.getPeople());
        setColName(data.getColName());
    }


    @Override
    public Object getImportantData() {
        return people;
    }


}
