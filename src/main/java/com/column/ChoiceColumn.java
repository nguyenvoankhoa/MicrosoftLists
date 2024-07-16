package com.column;

import com.column.datatype.Choice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceColumn extends Column implements IColumn<Choice> {
    private Choice choice;
    @Override
    public Choice getDefaultData() {
        return choice;
    }
    @Override
    public ColumnType getType() {
        return ColumnType.CHOICE;
    }
}
