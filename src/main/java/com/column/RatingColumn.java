package com.column;

import com.column.datatype.HyperLink;
import com.column.datatype.Rating;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

@Getter
@Setter
public class RatingColumn extends Column implements IColumn<Rating> {
    private Rating rating;

    public RatingColumn(String name) {
        super(name);
        setType(ColumnType.AVERAGE_RATING);
    }

    @Override
    public Rating getDefaultData() {
        return rating;
    }

    @Override
    public boolean checkConstraint(Rating data) {
        Predicate<Rating> requirePredicate = d -> !isRequire() || d != null;
        return requirePredicate.test(data);
    }
}
