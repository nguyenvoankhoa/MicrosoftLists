package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class DateAndTimeDTO {
    private Date date;
    private Time time;
    private ColumnType type;

    public DateAndTimeDTO(DateAndTime dateAndTime) {
        this.date = dateAndTime.getDate();
        this.time = dateAndTime.getTime();
        this.type = dateAndTime.getType();
    }
}
