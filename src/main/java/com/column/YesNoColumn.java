package com.column;

import com.column.datatype.YesNo;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

@Getter
@Setter
public class YesNoColumn extends Column implements IColumn<YesNo> {
    private YesNo data;

    public YesNoColumn(String name) {
        super(name);
        setType(ColumnType.YESNO);
    }

    @Override
    public YesNo getDefaultData() {
        return data;
    }

    @Override
    public boolean checkConstraint(YesNo data) {
        Predicate<YesNo> requirePredicate = d -> !isRequire() || d != null;
        return requirePredicate.test(data);
    }
}
