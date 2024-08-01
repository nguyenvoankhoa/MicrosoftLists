package com.model.column;

import com.model.datatype.Number;
import com.model.datatype.Rating;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class RatingColumn extends Column implements IColumn<Rating> {
    private Rating rating;

    private double maxRate = 5;

    private double minRate = 0;

    public RatingColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public Rating getDefaultData() {
        return rating;
    }

    @Override
    public void setDefaultData(String str) {
        setRating(Rating.builder().rate(Double.parseDouble(str)).build());
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        return Common.checkType(data.getClass(), Rating.class);
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        String[] arr = data.split(";");
        return new Rating(colName, arr);
    }

}
