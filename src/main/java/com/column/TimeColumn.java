package com.column;

import com.column.datatype.DateAndTime;
import com.column.factory.DataFactory;
import com.column.factory.TimeFactory;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TimeColumn implements IColumn<DateAndTime> {
    private String name;
    private static final ColumnType type = ColumnType.DATE_AND_TIME;
    private DateAndTime dateAndTime;

    @Override
    public DateAndTime getData() {
        return this.dateAndTime;
    }

    @Override
    public void setData(DateAndTime data) {
        this.dateAndTime = data;
    }

    @Override
    public DataFactory<?> getDataFactory() {
        return new TimeFactory();
    }
}
