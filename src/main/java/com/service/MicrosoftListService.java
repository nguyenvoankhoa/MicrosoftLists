package com.service;

import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.Template;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MicrosoftListService {

    public SmartList createList(MicrosoftList mls, String name) {
        if (Common.checkExist(mls, name)) {
            return null;
        }
        return createNewList(mls, name);
    }

    private SmartList createNewList(MicrosoftList mls, String name) {
        SmartList sl = new SmartList(name);
        mls.getListCollection().add(sl);
        return sl;
    }

    public boolean addFavourite(MicrosoftList mls, String name) {
        SmartList sl = getListByName(mls, name);
        if (sl != null) {
            mls.getFavouriteCollection().add(sl);
            return true;
        }
        return false;
    }


    public SmartList createListFromTemplate(MicrosoftList mls, Template t, String name) {
        SmartList sl = createNewList(mls, name);
        sl.setColumns(t.getColumns());
        return sl;
    }


    public SmartList getListByName(MicrosoftList mls, String name) {
        return mls.getListCollection().stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
