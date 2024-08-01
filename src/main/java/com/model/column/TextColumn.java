package com.model.column;

import com.model.Rule;
import com.model.datatype.Text;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextColumn extends Column implements IColumn<Text> {
    Text text;
    private int maxLength = Integer.MAX_VALUE;

    private Rule rule;

    public TextColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public Text getDefaultData() {
        return text;
    }

    @Override
    public void setDefaultData(String str) {
        setText(Text.builder().str(str).build());
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        return Common.checkType(data.getClass(), Text.class);
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        return new Text(colName, data);
    }

}
