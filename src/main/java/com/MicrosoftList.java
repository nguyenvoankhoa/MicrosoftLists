package com;

import com.permission.PermissionManagement;
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

    private JsonService jsonService;

    public MicrosoftList(String tplPath) throws IOException {
        initDefaultTemplate(tplPath);
    }

    public void initDefaultTemplate(String tplPath) throws IOException {
        templates = JsonService.loadTemplatesFromJson(tplPath);
        listCollection = new ArrayList<>();
        favouriteCollection = new ArrayList<>();
    }

    public boolean createList(String name) {
        return Common.checkExist(this, name) == null && createNewList(name) != null;
    }

    private SmartList createNewList(String name) {
        PermissionManagement pm = new PermissionManagement();
        SmartList sl = new SmartList(name, pm);
        this.listCollection.add(sl);
        return sl;
    }

    public void addFavourite(SmartList smartList) {
        this.favouriteCollection.add(smartList);
    }

    public void saveTemplate(Template smartList) {
        this.templates.add(smartList);
    }


    public SmartList createListFromTemplate(Template t, String name) {
        SmartList sl = createNewList(name);
        sl.setColumns(t.getColumns());
        return sl;
    }

}
