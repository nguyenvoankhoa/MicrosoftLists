package com;

import com.column.IColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Form {
    private List<IColumn> columns;


    public Form() {
        this.columns = new ArrayList<>();
    }

    public void addColumn(IColumn column) {
        this.columns.add(column);
    }

    public void removeColumn(IColumn column) {
        this.columns.remove(column);
    }

    public String generateFormRepresentation() {
        StringBuilder formRepresentation = new StringBuilder();
        for (IColumn column : columns) {
            formRepresentation.append(column.getDefaultData()).append("\n");
        }
        return formRepresentation.toString();
    }

}
