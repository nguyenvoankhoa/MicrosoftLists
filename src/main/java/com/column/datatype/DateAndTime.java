package com.column.datatype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateAndTime implements Data<DateAndTime>, Comparator<DateAndTime> {
    private Date date;
    private Time time;

    @Override
    public DateAndTime getData() {
        return this;
    }

    @Override
    public void setData(DateAndTime data){
         setDate(data.getDate());
         setTime(data.getTime());
    }

    @Override
    public Object getImportantData() {
        return this.date;
    }

    @Override
    public int compare(DateAndTime o1, DateAndTime o2) {
        return Comparator.comparing(DateAndTime::getDate)
                .thenComparing(DateAndTime::getTime)
                .compare(o2, o1);
    }
}
