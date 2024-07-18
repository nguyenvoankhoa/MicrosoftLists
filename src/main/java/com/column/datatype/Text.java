package com.column.datatype;

import com.column.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Text implements IData<Text>, Comparator<Text> {
    private String text;
    private ColumnType type = ColumnType.TEXT;

    public Text(String text) {
        this.text = text;
    }

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
    public ColumnType getType() {
        return ColumnType.TEXT;
    }


    @Override
    public int compare(Text o1, Text o2) {
        return o2.getText().compareTo(o1.getText());
    }
}
