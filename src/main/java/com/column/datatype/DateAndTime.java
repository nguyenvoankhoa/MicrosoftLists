package com.column.datatype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateAndTime implements Data<DateAndTime> {
    private Date date;
    private Time time;

    @Override
    public DateAndTime getData() {
        return this;
    }

    @Override
    public void setData(DateAndTime data){

    }
}
