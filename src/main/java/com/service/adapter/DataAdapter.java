package com.service.adapter;

import com.model.column.ColumnType;
import com.model.datatype.*;
import com.service.deserialize.ChoiceDeserializer;
import com.service.deserialize.PersonDeserializer;
import com.google.gson.*;
import com.model.datatype.Number;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class DataAdapter implements JsonSerializer<IData<?>>, JsonDeserializer<IData<?>> {
    private static final Map<ColumnType, Class<? extends IData<?>>> typeRegistry = new EnumMap<>(ColumnType.class);
    private static final Map<ColumnType, JsonDeserializer<? extends IData<?>>> deserializerRegistry = new EnumMap<>(ColumnType.class);

    static {
        typeRegistry.put(ColumnType.TEXT, Text.class);
        typeRegistry.put(ColumnType.CHOICE, MultipleChoice.class);
        typeRegistry.put(ColumnType.DATE_AND_TIME, DateAndTime.class);
        typeRegistry.put(ColumnType.HYPERLINK, HyperLink.class);
        typeRegistry.put(ColumnType.IMAGE, Image.class);
        typeRegistry.put(ColumnType.NUMBER, Number.class);
        typeRegistry.put(ColumnType.PERSON, MultiplePerson.class);
        typeRegistry.put(ColumnType.AVERAGE_RATING, Rating.class);
        typeRegistry.put(ColumnType.YESNO, YesNo.class);
        deserializerRegistry.put(ColumnType.CHOICE, new ChoiceDeserializer());
        deserializerRegistry.put(ColumnType.PEOPLE, new PersonDeserializer());
    }

    @Override
    public JsonElement serialize(IData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty("type", src.getType().name());
        return jsonObject;
    }

    @Override
    public IData<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        ColumnType type = ColumnType.valueOf(jsonObject.get("type").getAsString());

        return Optional.ofNullable(deserializerRegistry.get(type))
                .map(deserializer -> deserializer.deserialize(json, typeOfT, context))
                .orElseGet(() -> {
                    Class<? extends IData<?>> clazz = Optional.ofNullable(typeRegistry.get(type))
                            .orElseThrow(() -> new JsonParseException("Unknown type: " + type));
                    return context.deserialize(jsonObject, clazz);
                });
    }
}
