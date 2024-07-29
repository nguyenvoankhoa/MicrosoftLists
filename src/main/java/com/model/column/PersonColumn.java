package com.model.column;

import com.model.datatype.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Getter
@Setter
public class PersonColumn extends Column implements IColumn<List<Person>> {
    private List<Person> people;
    private boolean isMultiSelect;

    public PersonColumn(String name) {
        super(name);
        isMultiSelect = false;
        this.people = new ArrayList<>();
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
    public boolean checkConstraint(List<Person> data) {
        Predicate<List<Person>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Person>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;
        return requirePredicate.and(multiSelectPredicate).test(data);
    }
}
