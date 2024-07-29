package com.service;

import com.exception.ExistException;
import com.exception.NotFoundException;
import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.Template;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MicrosoftListService {

    public SmartList createList(MicrosoftList ml, String name) {
        if (Common.checkExist(ml, name)) {
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
        SmartList sl = getListByName(ml, name);
        ml.getFavouriteCollection().add(sl);
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

}
