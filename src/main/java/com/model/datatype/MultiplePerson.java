package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MultiplePerson implements IData<List<Person>> {
    List<Person> people;

    private ColumnType type = ColumnType.PEOPLE;

    public MultiplePerson() {
        this.people = new ArrayList<>();
    }

    @Override
    public List<Person> getData() {
        return this.people;
    }

    @Override
    public void setData(List<Person> data) {
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


}
