package com.column.datatype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Text implements Data<Text>, Comparator<Text> {
    private String text;

    @Override
    public Text getData() {
        return this;
    }

    @Override
    public void setData(Text data) {
        setText(data.getText());
    }

    @Override
    public Object getImportantData() {
        return this.text;
    }


    @Override
    public int compare(Text o1, Text o2) {
        return o2.getText().compareTo(o1.getText());
    }
}
