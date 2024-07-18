package com.column.datatype;

import com.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class Rating implements IData<Rating>, Comparator<Rating> {
    private double rate;
    private ColumnType type = ColumnType.AVERAGE_RATING;

    public Rating(double rate) {
        this.rate = rate;
    }

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
    public ColumnType getType() {
        return ColumnType.AVERAGE_RATING;
    }

    @Override
    public int compare(Rating o1, Rating o2) {
        return Comparator.comparing(Rating::getRate)
                .compare(o2, o1);
    }
}
