package com.column.factory;

import com.column.datatype.Data;
import com.column.datatype.DateAndTime;

public class TimeFactory implements DataFactory<DateAndTime>{
    @Override
    public Data<DateAndTime> createData() {
        return new DateAndTime();
    }
}
