package com.model.column;

import com.model.datatype.Person;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    public void checkConstraint(Object data) {
        Predicate<List<Person>> requirePredicate = d -> !isRequire() || !d.isEmpty();
        Predicate<List<Person>> multiSelectPredicate = d -> isMultiSelect() || d.size() <= 1;
        Common.checkValid(requirePredicate.and(multiSelectPredicate).test((List<Person>) data));
    }

    @Override
    public List<Person> createSimpleData(Object data) {
        List<String> lists = (List<String>) data;
        return lists.stream()
                .map(Person::new)
                .toList();
    }
}
