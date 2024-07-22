package com.column;

import com.column.datatype.Choice;
import com.column.datatype.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class PersonColumn extends Column implements IColumn<List<Person>> {
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
    public boolean checkConstraint(List<Person> data) {
        Predicate<List<Person>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Person>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;
        return requirePredicate.test(data) && multiSelectPredicate.test(data);
    }
}
