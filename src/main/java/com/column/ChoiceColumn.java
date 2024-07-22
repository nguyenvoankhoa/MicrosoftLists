package com.column;

import com.column.datatype.Choice;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class ChoiceColumn extends Column implements IColumn<Object> {
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
    public boolean checkConstraint(Object data) {
        Predicate<List<Choice>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Choice>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;

        return Optional.ofNullable(data)
                .map(this::convertToList)
                .map(list -> requirePredicate.and(multiSelectPredicate).test(list))
                .orElse(false);
    }

    private List<Choice> convertToList(Object data) {
        return Optional.ofNullable(data)
                .flatMap(d -> Optional.of(d instanceof List)
                        .filter(isList -> isList)
                        .map(isList -> (List<Choice>) d)
                        .or(() -> Optional.of(Stream.of(d)
                                .filter(Choice.class::isInstance)
                                .map(Choice.class::cast)
                                .toList())))
                .orElseGet(List::of);
    }

}
