package com.column.datatype;

import com.column.ColumnType;
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
public class DateAndTime implements IData<DateAndTime>, Comparator<DateAndTime> {
    private Date date;
    private Time time;
    private ColumnType type = ColumnType.DATE_AND_TIME;

    public DateAndTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

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
    public ColumnType getType() {
        return ColumnType.DATE_AND_TIME;
    }

    @Override
    public int compare(DateAndTime o1, DateAndTime o2) {
        return Comparator.comparing(DateAndTime::getDate)
                .thenComparing(DateAndTime::getTime)
                .compare(o2, o1);
    }
}
