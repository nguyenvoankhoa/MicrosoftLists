package com.factory;

import com.model.column.*;

import java.util.EnumMap;
import java.util.Map;

public class ColumnFactory {
    private final Map<ColumnType, IColumn<?>> factory = new EnumMap<>(ColumnType.class);

    public ColumnFactory(String name, ColumnType columnType) {
        factory.put(ColumnType.TEXT, new TextColumn(name, columnType));
        factory.put(ColumnType.DATE_AND_TIME, new TimeColumn(name, columnType));
        factory.put(ColumnType.NUMBER, new NumberColumn(name, columnType));
        factory.put(ColumnType.YESNO, new YesNoColumn(name, columnType));
        factory.put(ColumnType.CHOICE, new ChoiceColumn(name, columnType));
        factory.put(ColumnType.HYPERLINK, new HyperLinkColumn(name, columnType));
        factory.put(ColumnType.IMAGE, new ImageColumn(name, columnType));
        factory.put(ColumnType.PERSON, new PersonColumn(name, columnType));
        factory.put(ColumnType.AVERAGE_RATING, new RatingColumn(name, columnType));
        factory.put(ColumnType.MULTIPLE_CHOICE, new ChoiceColumn(name, columnType));
        factory.put(ColumnType.MULTIPLE_PERSON, new PersonColumn(name, columnType));
    }

    public IColumn getColumn(ColumnType type) {
        return factory.get(type);
    }
}
