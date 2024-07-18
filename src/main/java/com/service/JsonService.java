package com.service;

import com.SmartList;
import com.Template;
import com.column.IColumn;
import com.column.adapter.ColumnAdapter;
import com.column.adapter.DataAdapter;
import com.column.datatype.IData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonService {
    private JsonService() {
    }

    public static boolean saveToJson(Template list, String filepath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        Path path = Paths.get(filepath);
        Files.writeString(path, json);
        return true;
    }

    public static SmartList loadSmartListFromJson(String filePath) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IColumn.class, new ColumnAdapter())
                .registerTypeAdapter(IData.class, new DataAdapter())
                .create();
        Path path = Paths.get(filePath);
        String json = Files.readString(path);
        return gson.fromJson(json, SmartList.class);
    }

    public static List<Template> loadTemplatesFromJson(String filePath) throws IOException {
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
