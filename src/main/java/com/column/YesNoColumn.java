package com.column;

import com.column.factory.DataFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumn implements IColumn<Boolean> {
    private String name;
    private static final ColumnType type = ColumnType.YESNO;

    private Boolean data;

    @Override
    public Boolean getData() {
        return this.data;
    }

    @Override
    public void setData(Boolean data) {
        this.data = data;
    }

    @Override
    public DataFactory<?> getDataFactory() {
        return null;
    }
}
