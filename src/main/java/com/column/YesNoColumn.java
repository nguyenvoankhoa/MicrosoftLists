package com.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumn extends Column implements IColumn<Boolean> {
    private Boolean data;
    @Override
    public Boolean getDefaultData() {
        return data;
    }
    @Override
    public ColumnType getType() {
        return ColumnType.YESNO;
    }
}
