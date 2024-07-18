package com.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {
    private String name;
    private boolean isVisible;

    private ColumnType type;

    public Column(String name) {
        this.name = name;
        this.isVisible = true;
    }
}
