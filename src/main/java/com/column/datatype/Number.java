package com.column.datatype;

import com.column.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Number implements IData<Number>, Comparator<Number> {
    private double num;
    private byte[] icon;
    private ColumnType type = ColumnType.NUMBER;
    public Number(double num) {
        this.num = num;
    }

    @Override
    public Number getData() {
        return this;
    }

    @Override
    public void setData(Number data) {
        setNum(data.getNum());
        setIcon(data.getIcon());
    }

    @Override
    public Object getImportantData() {
        return this.num;
    }

    @Override
    public int compare(Number o1, Number o2) {
        return Comparator.comparing(Number::getNum)
                .compare(o2, o1);
    }
}
