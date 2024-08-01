package com.model.column;

import com.model.datatype.YesNo;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

@Getter
@Setter
public class YesNoColumn extends Column implements IColumn<YesNo> {
    private YesNo data;

    public YesNoColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public YesNo getDefaultData() {
        return data;
    }

    @Override
    public void setDefaultData(String str) {
        setData(YesNo.builder().isChosen(Boolean.parseBoolean(str)).build());
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        return isRequire() && data == null;
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        return new YesNo(colName, Boolean.parseBoolean(data));
    }
}
