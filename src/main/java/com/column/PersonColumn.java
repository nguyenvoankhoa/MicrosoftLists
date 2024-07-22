package com.column;

import com.column.datatype.Choice;
import com.column.datatype.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Getter
@Setter
public class PersonColumn extends Column implements IColumn<Object> {
    private List<Person> people;
    private boolean isMultiSelect;

    public PersonColumn(String name) {
        super(name);
        isMultiSelect = false;
        setType(ColumnType.PERSON);
    }

    @Override
    public List<Person> getDefaultData() {
        return people;
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        Predicate<List<Person>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Person>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;

        return Optional.ofNullable(data)
                .map(this::convertToList)
                .map(list -> requirePredicate.and(multiSelectPredicate).test(list))
                .orElse(false);
    }

    private List<Person> convertToList(Object data) {
        return Optional.ofNullable(data)
                .flatMap(d -> Optional.of(d instanceof List)
                        .filter(isList -> isList)
                        .map(isList -> (List<Person>) d)
                        .or(() -> Optional.of(Stream.of(d)
                                .filter(Person.class::isInstance)
                                .map(Person.class::cast)
                                .toList())))
                .orElseGet(List::of);
    }
}
