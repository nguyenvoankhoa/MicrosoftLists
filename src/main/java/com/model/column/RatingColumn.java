package com.model.column;

import com.model.datatype.Rating;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

@Getter
@Setter
public class RatingColumn extends Column implements IColumn<Rating> {
    private Rating rating;

    private double maxRate = 5;

    private double minRate = 0;

    public RatingColumn(String name) {
        super(name);
        setType(ColumnType.AVERAGE_RATING);
    }

    @Override
    public Rating getDefaultData() {
        return rating;
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public void checkConstraint(Object data) {
        Predicate<Rating> requirePredicate = d -> !isRequire() || d != null;
        Predicate<Rating> minMaxPredicate = d -> d.getRate() <= maxRate && d.getRate() >= minRate;
        Common.checkValid(requirePredicate.and(minMaxPredicate).test((Rating) data));
    }

    @Override
    public Rating createSimpleData(Object data) {
        return new Rating((Double) data);
    }
}
