package com.column;

import com.column.datatype.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextColumn extends Column implements IColumn<Text> {
    private Text text;

    public TextColumn(String name) {
        super(name);
        setType(ColumnType.TEXT);
    }

    @Override
    public Text getDefaultData() {
        return text;
    }
}
