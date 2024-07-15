package com;

import io.micrometer.common.KeyValues;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartList extends Template{
    private List<Form> forms;
    private String id;
    private List<Item> items;
    private boolean isSave;
    public void createForm() {
        forms.add(new Form());
    }

    public void sort(Column column) {
    }

    public void filter(Column column) {
    }

    public void groupBy(Column column) {
    }

    public void createColumn() {
    }
}
