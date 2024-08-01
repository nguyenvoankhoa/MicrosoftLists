package com.model.column;

import com.model.datatype.Choice;
import com.model.datatype.MultipleChoice;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ChoiceColumn extends Column implements IColumn<List<Choice>> {
    private List<Choice> choices;

    public ChoiceColumn(String name, ColumnType columnType) {
        super(name);
        choices = new ArrayList<>();
        setType(columnType);
    }

    @Override
    public List<Choice> getDefaultData() {
        return choices;
    }


    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        if (isRequire() && data == null) return false;
        if (getColumnType() == ColumnType.MULTIPLE_CHOICE) {
            return data instanceof List<?>;
        }
        return data instanceof Choice;
    }

    @Override
    public void setDefaultData(String str) {
        setChoices(createSimpleData(str));
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        Common.checkNonExist(data);
        return getColumnType().equals(ColumnType.MULTIPLE_CHOICE)
                ? new MultipleChoice(colName, createSimpleData(data))
                : Choice.builder().colName(colName).name(data).build();
    }

    public List<Choice> createSimpleData(String data) {
        return Arrays.stream(data.split(";"))
                .map(c -> (Choice) Choice.builder().name(c).build())
                .collect(Collectors.toList());
    }


}
