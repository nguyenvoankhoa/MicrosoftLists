package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class Rating implements Data<Rating>, Comparator<Rating> {
    private double rate;

    @Override
    public Rating getData() {
        return this;
    }

    @Override
    public void setData(Rating data) {
        setRate(data.getRate());
    }

    @Override
    public Object getImportantData() {
        return this.rate;
    }

    @Override
    public int compare(Rating o1, Rating o2) {
        return Comparator.comparing(Rating::getRate)
                .compare(o2, o1);
    }
}
