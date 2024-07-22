package com.column;

import com.column.datatype.DateAndTime;
import com.column.datatype.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;


@Setter
@Getter
public class TimeColumn extends Column implements IColumn<DateAndTime> {
    private DateAndTime dateAndTime;

    public TimeColumn(String name) {
        super(name);
        setType(ColumnType.DATE_AND_TIME);
    }

    @Override
    public DateAndTime getDefaultData() {
        return dateAndTime;
    }

    @Override
    public boolean checkConstraint(DateAndTime data) {
        Predicate<DateAndTime> requirePredicate = d -> !isRequire() || d != null;
        return requirePredicate.test(data);
    }
}
