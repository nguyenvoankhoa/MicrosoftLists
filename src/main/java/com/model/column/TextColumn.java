package com.model.column;

import com.model.Rule;
import com.model.datatype.Number;
import com.model.datatype.Text;
import com.util.Common;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

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
        if (isRequire() && data == null) return false;
        Text txt = (Text) data;
        return txt.getStr().length() <= getMaxLength();
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        return new Text(colName, data);
    }

}
