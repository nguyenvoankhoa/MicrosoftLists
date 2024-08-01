package com.model.column;

import com.model.datatype.DateAndTime;
import com.util.DataConvert;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class TimeColumn extends Column implements IColumn<DateAndTime> {

    private DateAndTime dateAndTime;

    public TimeColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public DateAndTime getDefaultData() {
        return dateAndTime;
    }

    @Override
    public void setDefaultData(String str) {
        DataConvert dc = new DataConvert();
        Date date = dc.convertStringToDate(str);
        setDateAndTime(DateAndTime.builder().date(date).build());
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        return isRequire() && data == null;
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        return new DateAndTime(colName, data);
    }
}
