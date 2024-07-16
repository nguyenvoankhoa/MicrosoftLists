package com.column.factory;

import com.column.datatype.Choice;
import com.column.datatype.Data;

public class ChoiceFactory implements DataFactory<Choice> {
    @Override
    public Data<Choice> createData() {
        return new Choice();
    }
}
