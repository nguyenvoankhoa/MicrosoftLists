package com.model;

import com.model.column.IColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    private String name;
    private List<IColumn> columns;

    public Template(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
    }
}
