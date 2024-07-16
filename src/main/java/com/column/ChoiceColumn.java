package com.column;

import com.column.datatype.Choice;
import com.column.factory.ChoiceFactory;
import com.column.factory.DataFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceColumn implements IColumn<Choice> {
    private String name;
    private static final ColumnType type = ColumnType.CHOICE;
    private Choice choice;


    @Override
    public Choice getData() {
        return this.choice;
    }

    @Override
    public void setData(Choice data) {
        this.choice = data;
    }

    @Override
    public DataFactory<?> getDataFactory() {
        return new ChoiceFactory();
    }
}
