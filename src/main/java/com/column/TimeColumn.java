package com.column;

import com.column.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TimeColumn extends Column implements IColumn<DateAndTime> {
    private DateAndTime dateAndTime;
    @Override
    public DateAndTime getDefaultData() {
        return dateAndTime;
    }
    @Override
    public ColumnType getType() {
        return ColumnType.DATE_AND_TIME;
    }
}
