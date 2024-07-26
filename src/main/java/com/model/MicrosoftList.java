package com.model;

import com.service.PermissionManagement;
import com.service.Common;
import com.service.JsonService;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MicrosoftList {

    private List<Template> templates;
    private List<SmartList> listCollection;
    private List<SmartList> favouriteCollection;

    public MicrosoftList(String tplPath, JsonService js) throws IOException {
        initDefaultTemplate(js, tplPath);
    }

    public void initDefaultTemplate(JsonService js, String tplPath) throws IOException {
        templates = js.loadTemplatesFromJson(tplPath);
        listCollection = new ArrayList<>();
        favouriteCollection = new ArrayList<>();
    }


}
