package com.column.datatype;

import com.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lookup implements IData<Lookup> {
    private ColumnType type = ColumnType.LOOKUP;
    @Override
    public Lookup getData() {
        return null;
    }

    @Override
    public void setData(Lookup data) {
        // lookup
    }

    @Override
    public Object getImportantData() {
        return null;
    }

    @Override
    public ColumnType getType() {
        return null;
    }
}
