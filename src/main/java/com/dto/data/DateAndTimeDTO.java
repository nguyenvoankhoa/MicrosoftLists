package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class DateAndTimeDTO extends  BaseDataDTO{
    private Date date;
    private Time time;
    private ColumnType type;

}
