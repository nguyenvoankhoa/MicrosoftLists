package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Data
@SuperBuilder
public class Text extends BaseData implements IData<Text>, Comparator<Text> {
    private String str;
    private final ColumnType type = ColumnType.TEXT;

    public Text(String colName, String str) {
        super(colName);
        this.str = str;
    }

    @Override
    public Text getData() {
        return this;
    }

    @Override
    public void setData(Text data) {
        setStr(data.getStr());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.str;
    }


    @Override
    public int compare(Text o1, Text o2) {
        return o2.getStr().compareTo(o1.getStr());
    }
}
