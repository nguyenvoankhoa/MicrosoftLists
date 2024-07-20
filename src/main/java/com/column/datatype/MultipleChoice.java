package com.column.datatype;

import com.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MultipleChoice implements IData<Object> {
    List<Choice> choices;
    private boolean isMultiple;

    public MultipleChoice() {
        this.choices = new ArrayList<>();
        this.isMultiple = true;
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
        Optional.of(isMultiple)
                .filter(multiple -> multiple)
                .ifPresentOrElse(
                        multiple -> getChoices().add(choice),
                        () -> getChoices().get(0).setData(choice)
                );
    }

    private void addMultipleChoices(List<Choice> choices) {
        Optional.of(isMultiple)
                .filter(multiple -> multiple)
                .ifPresentOrElse(
                        multiple -> getChoices().addAll(choices),
                        () -> getChoices().get(0).setData(choices.get(0))
                );
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
