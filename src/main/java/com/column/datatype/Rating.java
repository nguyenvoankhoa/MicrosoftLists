package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating implements Data<Rating> {
    private double rate;

    @Override
    public Rating getData() {
        return this;
    }

    @Override
    public void setData(Rating data) {
        setRate(data.getRate());
    }
}
