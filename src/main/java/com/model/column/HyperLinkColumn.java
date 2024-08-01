package com.model.column;

import com.model.datatype.HyperLink;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class HyperLinkColumn extends Column implements IColumn<HyperLink> {
    private HyperLink hyperLink;

    public HyperLinkColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public HyperLink getDefaultData() {
        return hyperLink;
    }

    @Override
    public void setDefaultData(String str) {
        setHyperLink(new HyperLink(str));
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        Common.checkExist(data);
        return Common.checkType(data.getClass(), HyperLink.class);
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        String[] parts = data.split(";");
        return new HyperLink(colName, parts);
    }

}
