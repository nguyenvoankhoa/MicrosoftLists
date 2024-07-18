package com.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumn extends Column implements IColumn<Boolean> {
    private Boolean data;

    public YesNoColumn(String name) {
        super(name);
        setType(ColumnType.YESNO);
    }

    @Override
    public Boolean getDefaultData() {
        return data;
    }
}
