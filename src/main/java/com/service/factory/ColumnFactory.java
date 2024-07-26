package com.service.factory;

import com.column.*;
import com.model.column.*;

import java.util.EnumMap;
import java.util.Map;

public class ColumnFactory {
    private final Map<ColumnType, IColumn<?>> factory = new EnumMap<>(ColumnType.class);

    public ColumnFactory(String name) {
        factory.put(ColumnType.TEXT, new TextColumn(name));
        factory.put(ColumnType.DATE_AND_TIME, new TimeColumn(name));
        factory.put(ColumnType.NUMBER, new NumberColumn(name));
        factory.put(ColumnType.YESNO, new YesNoColumn(name));
        factory.put(ColumnType.CHOICE, new ChoiceColumn(name));
        factory.put(ColumnType.HYPERLINK, new HyperLinkColumn(name));
        factory.put(ColumnType.IMAGE, new ImageColumn(name));
        factory.put(ColumnType.LOOKUP, new LookupColumn(name));
        factory.put(ColumnType.PERSON, new PersonColumn(name));
        factory.put(ColumnType.AVERAGE_RATING, new RatingColumn(name));
    }

    public IColumn<?> getColumn(ColumnType type) {
        return factory.get(type);
    }
}
