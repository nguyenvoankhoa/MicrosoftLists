package com.column.adapter;

import com.column.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ColumnAdapter implements JsonSerializer<IColumn<?>>, JsonDeserializer<IColumn<?>> {
    private static final Map<ColumnType, Class<? extends IColumn<?>>> typeRegistry = new EnumMap<>(ColumnType.class);

    static {
        typeRegistry.put(ColumnType.TEXT, TextColumn.class);
        typeRegistry.put(ColumnType.CHOICE, ChoiceColumn.class);
        typeRegistry.put(ColumnType.AVERAGE_RATING, RatingColumn.class);
        typeRegistry.put(ColumnType.PERSON, PersonColumn.class);
        typeRegistry.put(ColumnType.YESNO, YesNoColumn.class);
        typeRegistry.put(ColumnType.IMAGE, ImageColumn.class);
        typeRegistry.put(ColumnType.DATE_AND_TIME, TimeColumn.class);
        typeRegistry.put(ColumnType.NUMBER, NumberColumn.class);
        typeRegistry.put(ColumnType.HYPERLINK, HyperLinkColumn.class);
    }

    @Override
    public JsonElement serialize(IColumn<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty("type", src.getColumnType().name());
        return jsonObject;
    }

    @Override
    public IColumn<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        ColumnType type = ColumnType.valueOf(jsonObject.get("type").getAsString());
        Class<? extends IColumn<?>> clazz = typeRegistry.get(type);
        if (clazz == null) {
            throw new JsonParseException("Unknown element type: " + type);
        }
        return context.deserialize(jsonObject, clazz);
    }

}
