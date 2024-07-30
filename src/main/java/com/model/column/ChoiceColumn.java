package com.model.column;

import com.model.datatype.Choice;
import com.util.Common;
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
    public void checkConstraint(Object data) {
        Predicate<List<Choice>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Choice>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;
        Common.checkValid(requirePredicate.and(multiSelectPredicate).test((List<Choice>) data));
    }

    @Override
    public List<Choice> createSimpleData(Object data) {
        List<String> lists = (List<String>) data;
        return lists.stream()
                .map(Choice::new)
                .toList();
    }

}
