package com;


import java.util.ArrayList;
import java.util.List;

public class MicrosoftList {

    public List<Template> templates;
    public List<SmartList> listCollection;
    public List<SmartList> favouriteCollection;

    public void initDefaultTemplate() {
        templates = new ArrayList<>();
        listCollection = new ArrayList<>();
        favouriteCollection = new ArrayList<>();
    }

    public void createList(SmartList smartList) {
        this.listCollection.add(smartList);
    }

    public void addFavourite(SmartList smartList) {
        this.favouriteCollection.add(smartList);
    }

    public void remove(String lId) {
        this.listCollection.removeIf(l -> l.getId().equals(lId));
    }

    public void saveTemplate(SmartList smartList) {
        this.templates.add(smartList);
    }

    public void exportToCSV(SmartList smartList) {
    }

    public void restore() {
    }

    public void saveList(SmartList smartList) {
        smartList.setSave(true);
    }
}
