package com.model.column;

import com.model.datatype.Choice;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class ChoiceColumn extends Column implements IColumn<List<Choice>> {
    private List<Choice> choices;
    private boolean isMultiSelect;

    public ChoiceColumn(String name) {
        super(name);
        choices = new ArrayList<>();
        isMultiSelect = false;
        setType(ColumnType.CHOICE);
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
    public boolean checkConstraint(List<Choice> data) {
        Predicate<List<Choice>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Choice>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;
        return requirePredicate.and(multiSelectPredicate).test(data);
    }

    @Override
    public List<Choice> createSimpleData(Object data) {
        List<String> lists = (List<String>) data;
        return lists.stream()
                .map(Choice::new)
                .toList();
    }

}
