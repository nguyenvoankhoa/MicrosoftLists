package com.column.datatype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Data<Person> {
    private String name;
    private byte[] image;

    @Override
    public Person getData() {
        return this;
    }

    @Override
    public void setData(Person data) {
        setName(data.getName());
        setImage(data.getImage());
    }
}
