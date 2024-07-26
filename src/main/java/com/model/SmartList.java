package com.model;

import com.model.view.View;
import com.service.PermissionManagement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class SmartList extends Template {
    private List<Form> forms;
    private List<Row> rows;
    private boolean isSave;
    private List<View> views;
    private PermissionManagement permissionManagement;

    public SmartList(String title, PermissionManagement pm) {
        super(title);
        this.forms = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.views = new ArrayList<>();
        this.permissionManagement = pm;
    }


}
