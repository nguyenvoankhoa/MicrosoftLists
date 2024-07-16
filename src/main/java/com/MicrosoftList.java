package com;


import com.export.ExportCSV;
import com.export.ExportHandler;
import com.export.ExportResult;
import com.export.FileType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MicrosoftList {

    public List<Template> templates;
    public List<SmartList> listCollection;
    public List<SmartList> favouriteCollection;

    public MicrosoftList() {
        initDefaultTemplate();
    }

    public void initDefaultTemplate() {
        templates = new ArrayList<>();
        templates.add(new Template("Issue Tracker"));
        templates.add(new Template("Employee onboarding"));
        templates.add(new Template("Asset manager"));
        templates.add(new Template("Recruitment tracker"));
        listCollection = new ArrayList<>();
        favouriteCollection = new ArrayList<>();
    }

    public void createList(String title) {
        SmartList smartList = new SmartList(title);
        this.listCollection.add(smartList);
    }

    public void addFavourite(SmartList smartList) {
        this.favouriteCollection.add(smartList);
    }

    public void remove(String lId) {
        this.listCollection.removeIf(l -> l.getId().equals(lId));
    }

    public void saveTemplate(Template smartList) {
        this.templates.add(smartList);
    }

    public ExportResult exportToCSV(SmartList smartList, String filename) {
        return ExportHandler.export(smartList, filename, FileType.CSV);
    }

    public void saveList(SmartList smartList) {
        smartList.setSave(true);
    }
}
