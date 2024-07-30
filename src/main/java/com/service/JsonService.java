package com.service;

import com.model.MicrosoftList;
import com.model.Template;
import com.model.column.IColumn;
import com.adapter.ColumnAdapter;
import com.adapter.DataAdapter;
import com.model.datatype.IData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class JsonService {
    private static final Logger LOGGER = Logger.getLogger(JsonService.class.getName());

    public boolean saveToJson(MicrosoftList ml, String filepath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ml);
        Path path = Paths.get(filepath);
        try {
            Files.writeString(path, json);
            LOGGER.log(Level.INFO, "Successfully saved MicrosoftList to JSON file at {0}", filepath);
            return true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save MicrosoftList to JSON file at {0} , {1}" + filepath, e);
            return false;
        }
    }

    public MicrosoftList loadListsFromJson(String filePath) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IColumn.class, new ColumnAdapter())
                .registerTypeAdapter(IData.class, new DataAdapter())
                .create();
        Path path = Paths.get(filePath);
        try {
            String json = Files.readString(path);
            Type listType = new TypeToken<MicrosoftList>() {
            }.getType();
            return gson.fromJson(json, listType);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load SmartList from JSON file at " + filePath, e);
            return null;
        }
    }


    public List<Template> loadTemplatesFromJson(String filePath) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IColumn.class, new ColumnAdapter())
                .registerTypeAdapter(IData.class, new DataAdapter())
                .create();
        Path path = Paths.get(filePath);
        String json = Files.readString(path);
        Type templateListType = new TypeToken<List<Template>>() {
        }.getType();
        return gson.fromJson(json, templateListType);
    }
}
