package com.column;

import com.column.datatype.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextColumn extends Column implements IColumn<Text> {
    private Text text;

    public ColumnType getType() {
        return ColumnType.TEXT;
    }

    @Override
    public Text getDefaultData() {
        return text;
    }
}
