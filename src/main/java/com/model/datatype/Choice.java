package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;


@Data
@SuperBuilder
public class Choice extends BaseData implements IData<Choice>, Comparator<Choice> {
    private String name;
    private final ColumnType type = ColumnType.CHOICE;

    @Override
    public Choice getData() {
        return this;
    }

    @Override
    public void setData(Choice data) {
        setName(data.getName());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.name;
    }

    @Override
    public int compare(Choice o1, Choice o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
