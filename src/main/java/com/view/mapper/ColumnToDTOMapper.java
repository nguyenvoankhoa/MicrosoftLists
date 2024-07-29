package com.view.mapper;

import com.model.column.*;
import com.model.column.IColumn;
import com.view.column.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ColumnToDTOMapper {

    private static final Map<Class<? extends IColumn<?>>, Function<IColumn<?>, Object>> mapper = new HashMap<>();

    static {
        mapper.put(HyperLinkColumn.class, column -> new HyperLinkColumnDTO((HyperLinkColumn) column));
        mapper.put(ImageColumn.class, column -> new ImageColumnDTO((ImageColumn) column));
        mapper.put(NumberColumn.class, column -> new NumberColumnDTO((NumberColumn) column));
        mapper.put(PersonColumn.class, column -> new PersonColumnDTO((PersonColumn) column));
        mapper.put(RatingColumn.class, column -> new RatingColumnDTO((RatingColumn) column));
        mapper.put(TextColumn.class, column -> new TextColumnDTO((TextColumn) column));
        mapper.put(TimeColumn.class, column -> new TimeColumnDTO((TimeColumn) column));
        mapper.put(YesNoColumn.class, column -> new YesNoColumnDTO((YesNoColumn) column));
        mapper.put(ChoiceColumn.class, column -> new ChoiceColumnDTO((ChoiceColumn) column));
    }

    public static Object map(IColumn<?> column) {
        Function<IColumn<?>, Object> function = mapper.get(column.getClass());
        if (function != null) {
            return function.apply(column);
        } else {
            throw new IllegalArgumentException("No DTO mapping found for class: " + column.getClass());
        }
    }
}

