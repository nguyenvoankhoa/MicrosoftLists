package com.column;

import com.column.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;


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
}
