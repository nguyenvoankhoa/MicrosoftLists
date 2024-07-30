package com.model.column;

import com.model.Rule;
import com.model.datatype.Text;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

@Getter
@Setter
public class TextColumn extends Column implements IColumn<Text> {
    private Text text;
    private int maxLength = Integer.MAX_VALUE;

    private Rule rule;

    public TextColumn(String name) {
        super(name);
        setType(ColumnType.TEXT);
    }

    @Override
    public Text getDefaultData() {
        return text;
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public void checkConstraint(Object data) {
        Predicate<Text> requirePredicate = d -> !isRequire() || d != null;
        Predicate<Text> maxLengthPredicate = d -> maxLength == Integer.MAX_VALUE
                || d.getStr().length() <= getMaxLength();
        Common.checkValid(requirePredicate.and(maxLengthPredicate).test((Text) data));
    }

    @Override
    public Text createSimpleData(Object data) {
        return new Text((String) data);
    }
}
