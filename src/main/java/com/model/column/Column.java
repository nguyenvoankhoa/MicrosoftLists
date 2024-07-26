package com.model.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {
    private String name;
    private boolean isVisible;

    private boolean isRequire;

    private ColumnType type;

    public Column(String name) {
        this.name = name;
        this.isVisible = true;
    }
}
