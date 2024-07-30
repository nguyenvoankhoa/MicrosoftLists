package com.adapter;

import com.model.datatype.Choice;
import com.model.datatype.MultipleChoice;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChoiceDeserializer implements JsonDeserializer<MultipleChoice> {
    @Override
    public MultipleChoice deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        MultipleChoice multipleChoice = new MultipleChoice();
        JsonElement choicesElement = jsonObject.get("choices");
        List<Choice> choices = new ArrayList<>();
        for (JsonElement element : choicesElement.getAsJsonArray()) {
            Choice choice = context.deserialize(element, Choice.class);
            choices.add(choice);
        }
        multipleChoice.setChoices(choices);

        return multipleChoice;
    }
}
