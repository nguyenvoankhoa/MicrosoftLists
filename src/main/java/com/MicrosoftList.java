package com;

import com.permission.PermissionManagement;
import com.service.JsonService;
import com.export.ExportHandler;
import com.export.ExportResult;
import com.export.FileType;
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
        return checkExist(name) == null && createNewList(name) != null;
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

    public void remove(String lName) {
        this.listCollection.removeIf(l -> l.getName().equals(lName));
    }

    public void saveTemplate(Template smartList) {
        this.templates.add(smartList);
    }

    public ExportResult exportToCSV(SmartList smartList, String filename) {
        return ExportHandler.export(smartList, filename, FileType.CSV);
    }

    public SmartList checkExist(String name) {
        return getListCollection().stream().filter(s -> s.getName().equals(name))
                .findFirst().orElse(null);
    }

    public SmartList createListFromTemplate(Template t, String name) {
        SmartList sl = createNewList(name);
        sl.setColumns(t.getColumns());
        return sl;
    }

}
