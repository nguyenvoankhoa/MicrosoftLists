package com.model.datatype;

import com.model.column.ColumnType;
import com.util.DataConvert;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;
import java.util.Date;

@Data
@SuperBuilder
public class DateAndTime extends BaseData implements IData<DateAndTime>, Comparator<DateAndTime> {
    private Date date;
    private final ColumnType type = ColumnType.DATE_AND_TIME;

    public DateAndTime(String colName, String data) {
        super(colName);
        DataConvert dc = new DataConvert();
        this.date = dc.convertStringToDate(data);
    }

    @Override
    public DateAndTime getData() {
        return this;
    }

    @Override
    public void setData(DateAndTime data) {
        setDate(data.getDate());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.date;
    }


    @Override
    public int compare(DateAndTime o1, DateAndTime o2) {
        return Comparator.comparing(DateAndTime::getDate)
                .compare(o2, o1);
    }
}
