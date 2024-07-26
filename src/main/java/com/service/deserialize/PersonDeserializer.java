package com.service.deserialize;

import com.model.datatype.MultiplePerson;
import com.model.datatype.Person;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersonDeserializer  implements JsonDeserializer<MultiplePerson> {
    @Override
    public MultiplePerson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        MultiplePerson multipleChoice = new MultiplePerson();
        JsonElement choicesElement = jsonObject.get("people");
        List<Person> people = new ArrayList<>();
        for (JsonElement element : choicesElement.getAsJsonArray()) {
            Person p = context.deserialize(element, Person.class);
            people.add(p);
        }
        multipleChoice.setPeople(people);

        return multipleChoice;
    }
}

