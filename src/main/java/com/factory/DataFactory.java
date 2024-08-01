package com.factory;

import com.model.column.ColumnType;
import com.model.datatype.*;
import com.model.datatype.Number;

import java.util.EnumMap;
import java.util.Map;

public class DataFactory {
    private final Map<ColumnType, IData> factory = new EnumMap<>(ColumnType.class);

    public DataFactory(String colName) {
        factory.put(ColumnType.TEXT, Text.builder().colName(colName).build());
        factory.put(ColumnType.DATE_AND_TIME, DateAndTime.builder().colName(colName).build());
        factory.put(ColumnType.NUMBER, Number.builder().colName(colName).build());
        factory.put(ColumnType.YESNO, YesNo.builder().colName(colName).build());
        factory.put(ColumnType.MULTIPLE_CHOICE, new MultipleChoice(colName));
        factory.put(ColumnType.CHOICE, Choice.builder().colName(colName).build());
        factory.put(ColumnType.HYPERLINK, HyperLink.builder().colName(colName).build());
        factory.put(ColumnType.IMAGE, new Image(colName));
        factory.put(ColumnType.PERSON, Person.builder().colName(colName).build());
        factory.put(ColumnType.MULTIPLE_PERSON, new MultiplePerson(colName));
    }


    public IData createData(ColumnType type) {
        return factory.get(type);
    }
}
