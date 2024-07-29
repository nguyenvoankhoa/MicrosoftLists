package com.view;

import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.Template;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MicrosoftListDTO {
    private List<Template> templates;
    private List<SmartListDTO> listCollection;
    private List<SmartListDTO> favouriteCollection;

    public MicrosoftListDTO(MicrosoftList microsoftList) {
        this.templates = microsoftList.getTemplates();
        this.listCollection = microsoftList.getListCollection().stream()
                .map(SmartListDTO::new)
                .toList();
        this.favouriteCollection = microsoftList.getFavouriteCollection().stream()
                .map(SmartListDTO::new)
                .toList();
    }
}
