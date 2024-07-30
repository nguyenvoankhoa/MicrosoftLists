package com.service;

import com.exception.ExistException;
import com.exception.NotFoundException;
import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.Template;
import com.util.Common;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MicrosoftListService {

    public SmartList createList(MicrosoftList ml, String name) {
        if (Common.checkExistName(ml, name)) {
            throw new ExistException();
        }
        return createNewList(ml, name);
    }

    private SmartList createNewList(MicrosoftList ml, String name) {
        SmartList sl = new SmartList(name);
        ml.getListCollection().add(sl);
        return sl;
    }

    public MicrosoftList addFavourite(MicrosoftList ml, String name) {
        String listName = ml.getFavouriteCollection().stream()
                .filter(c -> c.equals(name)).findFirst()
                .orElse(null);
        Common.checkExist(listName);
        ml.getFavouriteCollection().add(name);
        return ml;
    }


    public SmartList createListFromTemplate(MicrosoftList ml, Template t, String name) {
        SmartList sl = createNewList(ml, name);
        sl.setColumns(t.getColumns());
        return sl;
    }


    public SmartList getListByName(MicrosoftList ml, String name) {
        return ml.getListCollection().stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }


    public Template getTemplateByName(MicrosoftList ml, String name) {
        return ml.getTemplates().stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
