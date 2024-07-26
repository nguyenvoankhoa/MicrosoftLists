package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
public class Text implements IData<Text>, Comparator<Text> {
    private String str;
    private ColumnType type = ColumnType.TEXT;

    public Text(String text) {
        this.str = text;
    }

    @Override
    public Text getData() {
        return this;
    }

    @Override
    public void setData(Text data) {
        setStr(data.getStr());
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
