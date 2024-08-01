package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MultipleChoice extends BaseData  implements IData<MultipleChoice> {
    private List<Choice> choices;

    private final ColumnType type = ColumnType.MULTIPLE_CHOICE;

    public MultipleChoice(String colName) {
        super(colName);
        this.choices = new ArrayList<>();
    }

    public MultipleChoice(String colName, List<Choice> choices) {
        super(colName);
        this.choices = choices;
    }

    @Override
    public MultipleChoice getData() {
        return this;
    }

    @Override
    public void setData(MultipleChoice data) {
        setChoices(data.getChoices());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return choices;
    }


}
