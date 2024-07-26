package com.service;

import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.Template;
import org.springframework.beans.factory.annotation.Value;

public class MicrosoftListService {
    @Value("list.file.name")
    String listPath;

    private MicrosoftList microsoftList;

    public MicrosoftListService() {
        JsonService js = new JsonService();
        this.microsoftList = js.loadListsFromJson(listPath);
    }

    public SmartList createList(String name) {
        if (Common.checkExist(microsoftList, name)) {
            return null;
        }
        return createNewList(name);
    }

    private SmartList createNewList(String name) {
        PermissionManagement pm = new PermissionManagement();
        SmartList sl = new SmartList(name, pm);
        microsoftList.getListCollection().add(sl);
        return sl;
    }

    public boolean addFavourite(String name) {
        SmartList sl = getListByName(name);
        if (sl != null) {
            microsoftList.getFavouriteCollection().add(sl);
            return true;
        }
        return false;
    }

    public void saveTemplate(Template smartList) {
        microsoftList.getTemplates().add(smartList);
    }


    public SmartList createListFromTemplate(Template t, String name) {
        SmartList sl = createNewList(name);
        sl.setColumns(t.getColumns());
        return sl;
    }


    public SmartList getListByName(String name) {
        return microsoftList.getListCollection().stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
