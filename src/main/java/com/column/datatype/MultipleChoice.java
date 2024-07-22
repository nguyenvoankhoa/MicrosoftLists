package com.column.datatype;

import com.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MultipleChoice implements IData<Object> {
    List<Choice> choices;

    public MultipleChoice() {
        this.choices = new ArrayList<>();
    }

    @Override
    public Choice getData() {
        return this.choices.get(0);
    }

    @Override
    public void setData(Object data) {
        Optional.ofNullable(data)
                .filter(List.class::isInstance)
                .map(List.class::cast)
                .ifPresentOrElse(
                        this::addMultipleChoices,
                        () -> addSingleChoice((Choice) data)
                );
    }

    private void addSingleChoice(Choice choice) {
        Optional.of(getChoices())
                .filter(c -> !c.isEmpty())
                .ifPresent(c -> c.get(0).setData(choice));

        Optional.of(getChoices())
                .filter(List::isEmpty)
                .ifPresent(c -> c.add(choice));
    }

    private void addMultipleChoices(List<Choice> choices) {
        getChoices().addAll(choices);
    }


    @Override
    public Object getImportantData() {
        return choices;
    }

    @Override
    public ColumnType getType() {
        return ColumnType.CHOICE;
    }

}
