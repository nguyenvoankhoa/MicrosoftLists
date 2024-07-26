package com.model;

import com.model.column.IColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Form {
    private String name;
    private SmartList sm;
    private List<IColumn<?>> columns;

    public Form(SmartList smartList, List<IColumn<?>> columns, String name) {
        this.name = name;
        this.sm = smartList;
        this.columns = columns;
    }

    public void addColumn(IColumn<?> column) {
        this.columns.add(column);
        sm.getColumns().add(column);
    }

    public void removeColumn(IColumn<?> column) {
        this.columns.remove(column);
//        sm.removeColumn(column.getName());
    }

    public String generateFormRepresentation() {
        StringBuilder formRepresentation = new StringBuilder();
        for (IColumn column : columns) {
            formRepresentation.append(column.getDefaultData()).append("\n");
        }
        return formRepresentation.toString();
    }

}
