package com.column.datatype;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Choice implements Data<Choice> {
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
}
