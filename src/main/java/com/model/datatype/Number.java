package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Data
@SuperBuilder
public class Number extends BaseData implements IData<Number>, Comparator<Number> {
    private double num;
    private byte[] icon;
    private final ColumnType type = ColumnType.NUMBER;

    public Number(String colName, String... arg) {
        super(colName);
        if (arg.length > 0) {
            this.num = Double.parseDouble(arg[0]);
            this.icon = arg[0].getBytes();
        }

    }

    @Override
    public Number getData() {
        return this;
    }

    @Override
    public void setData(Number data) {
        setNum(data.getNum());
        setIcon(data.getIcon());
        setColName(data.getColName());
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
