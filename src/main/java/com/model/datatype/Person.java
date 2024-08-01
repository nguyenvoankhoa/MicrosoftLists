package com.model.datatype;

import com.model.column.ColumnType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Data
@SuperBuilder
public class Person extends BaseData  implements IData<Person>, Comparator<Person> {
    private String name;
    private byte[] image;
    private final ColumnType type = ColumnType.PERSON;

    public Person(String name, byte[] image, String colName) {
        super(colName);
        this.name = name;
        this.image = image;
    }


    @Override
    public Person getData() {
        return this;
    }

    @Override
    public void setData(Person data) {
        setName(data.getName());
        setImage(data.getImage());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.name;
    }


    @Override
    public int compare(Person o1, Person o2) {
        return Comparator.comparing(Person::getName)
                .compare(o2, o1);
    }
}
