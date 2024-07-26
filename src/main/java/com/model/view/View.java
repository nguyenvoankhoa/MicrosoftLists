package com.model.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class View {
    private String title;
    private ViewType viewType;
    private boolean isPublic;

    public View(String title) {
        this.title = title;
        this.isPublic = true;
    }
}
