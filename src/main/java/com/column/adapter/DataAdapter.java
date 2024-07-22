package com.column.adapter;

import com.column.ColumnType;
import com.column.datatype.*;
import com.column.datatype.Number;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DataAdapter implements JsonSerializer<IData>, JsonDeserializer<IData> {
    private static final Map<ColumnType, Class<? extends IData>> typeRegistry = new HashMap<>();

    static {
        typeRegistry.put(ColumnType.TEXT, Text.class);
        typeRegistry.put(ColumnType.CHOICE, Choice.class);
        typeRegistry.put(ColumnType.DATE_AND_TIME, DateAndTime.class);
        typeRegistry.put(ColumnType.HYPERLINK, HyperLink.class);
        typeRegistry.put(ColumnType.IMAGE, Image.class);
        typeRegistry.put(ColumnType.NUMBER, Number.class);
        typeRegistry.put(ColumnType.PERSON, Person.class);
        typeRegistry.put(ColumnType.AVERAGE_RATING, Rating.class);
        typeRegistry.put(ColumnType.YESNO, YesNo.class);

    }

    @Override
    public JsonElement serialize(IData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty("type", src.getType().name());
        return jsonObject;
    }

    @Override
    public IData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        ColumnType type = ColumnType.valueOf(jsonObject.get("type").getAsString());
        Class<? extends IData> clazz = typeRegistry.get(type);
        return context.deserialize(jsonObject, clazz);
    }
}
