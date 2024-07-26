package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MultipleChoice implements IData<List<Choice>> {
    List<Choice> choices;

    private ColumnType type = ColumnType.CHOICE;

    public MultipleChoice() {
        this.choices = new ArrayList<>();
    }

    @Override
    public List<Choice> getData() {
        return choices;
    }

    @Override
    public void setData(List<Choice> data) {
        setChoices(data);
    }

    @Override
    public Object getImportantData() {
        return choices;
    }


}
