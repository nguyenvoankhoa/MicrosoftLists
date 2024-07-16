package com.column.factory;

import com.column.*;

import java.util.EnumMap;
import java.util.Map;

public class ColumnFactory {
    private Map<ColumnType, IColumn> factory = new EnumMap<>(ColumnType.class);

    public ColumnFactory() {
        factory.put(ColumnType.TEXT, new TextColumn());
        factory.put(ColumnType.DATE_AND_TIME, new TimeColumn());
        factory.put(ColumnType.NUMBER, new NumberColumn());
        factory.put(ColumnType.YESNO, new YesNoColumn());
        factory.put(ColumnType.CHOICE, new ChoiceColumn());
        factory.put(ColumnType.HYPERLINK, new HyperLinkColumn());
        factory.put(ColumnType.IMAGE, new ImageColumn());
        factory.put(ColumnType.LOOKUP, new LookupColumn());
        factory.put(ColumnType.PERSON, new PersonColumn());
        factory.put(ColumnType.AVERAGE_RATING, new RatingColumn());
    }

    public IColumn<Object> getColumn(ColumnType type) {
        return factory.get(type);
    }
}
