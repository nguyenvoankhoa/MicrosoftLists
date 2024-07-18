package com.column.datatype;

import com.column.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements IData<Person>, Comparator<Person> {
    private String name;
    private byte[] image;
    private ColumnType type = ColumnType.PERSON;

    public Person(String name, byte[] image) {
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
    }

    @Override
    public Object getImportantData() {
        return this.name;
    }

    @Override
    public ColumnType getType() {
        return ColumnType.PERSON;
    }

    @Override
    public int compare(Person o1, Person o2) {
        return Comparator.comparing(Person::getName)
                .compare(o2, o1);
    }
}
