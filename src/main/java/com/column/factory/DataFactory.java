package com.column.factory;

import com.column.ColumnType;
import com.column.datatype.*;
import com.column.datatype.Number;

import java.util.EnumMap;
import java.util.Map;

public class DataFactory {
    private final Map<ColumnType, IData> factory = new EnumMap<>(ColumnType.class);

    public DataFactory() {
        factory.put(ColumnType.TEXT, new Text());
        factory.put(ColumnType.DATE_AND_TIME, new DateAndTime());
        factory.put(ColumnType.NUMBER, new Number());
        factory.put(ColumnType.YESNO, new YesNo());
        factory.put(ColumnType.CHOICE, new MultipleChoice());
        factory.put(ColumnType.HYPERLINK, new HyperLink());
        factory.put(ColumnType.IMAGE, new Image());
        factory.put(ColumnType.LOOKUP, new Lookup());
        factory.put(ColumnType.PERSON, new MultiplePerson());
    }


    public IData createData(ColumnType type) {
        return factory.get(type);
    }
}
