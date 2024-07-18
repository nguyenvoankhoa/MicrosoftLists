package com.view;

import com.Template;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class View extends Template {
    private ViewType viewType;

    public View(ViewType viewType, String title) {
        super(title);
        this.viewType = viewType;
    }
}
