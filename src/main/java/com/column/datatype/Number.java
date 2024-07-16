package com.column.datatype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Number implements Data<Number> {
    private double num;
    private byte[] icon;

    @Override
    public Number getData() {
        return this;
    }

    @Override
    public void setData(Number data) {
        setNum(data.getNum());
        setIcon(data.getIcon());
    }
}
