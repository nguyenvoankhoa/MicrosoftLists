package com.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MicrosoftList {
    private List<Template> templates;
    private List<SmartList> listCollection;
    private List<String> favouriteCollection;

    public MicrosoftList() {
        this.templates = new ArrayList<>();
        this.listCollection = new ArrayList<>();
        this.favouriteCollection = new ArrayList<>();
    }

}
