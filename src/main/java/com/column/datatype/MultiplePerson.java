package com.column.datatype;

import com.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MultiplePerson implements IData<Object> {
    List<Person> people;

    public MultiplePerson() {
        this.people = new ArrayList<>();
    }

    @Override
    public Person getData() {
        return this.people.get(0);
    }

    @Override
    public void setData(Object data) {
        Optional.ofNullable(data)
                .filter(List.class::isInstance)
                .map(List.class::cast)
                .ifPresentOrElse(
                        this::addMultipleChoices,
                        () -> addSinglePerson((Person) data)
                );
    }

    private void addSinglePerson(Person choice) {
        Optional.of(getPeople())
                .filter(p -> !p.isEmpty())
                .ifPresent(choices -> choices.get(0).setData(choice));

        Optional.of(getPeople())
                .filter(List::isEmpty)
                .ifPresent(choices -> choices.add(choice));
    }

    private void addMultipleChoices(List<Person> people) {
        getPeople().addAll(people);
    }


    @Override
    public Object getImportantData() {
        return people;
    }

    @Override
    public ColumnType getType() {
        return ColumnType.CHOICE;
    }

}
