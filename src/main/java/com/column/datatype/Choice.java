package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;


@Getter
@Setter
public class Choice implements Data<Choice>, Comparator<Choice> {
    private String name;
    private String shape;
    private String color;

    public Choice() {
        this.name = "Choice";
        this.shape = "ABc";
        this.color = "red";
    }


    @Override
    public Choice getData() {
        return this;
    }

    @Override
    public void setData(Choice data) {
        setName(data.getName());
        setShape(data.getShape());
        setColor(data.getColor());
    }

    @Override
    public Object getImportantData() {
        return this.name;
    }

    @Override
    public int compare(Choice o1, Choice o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
