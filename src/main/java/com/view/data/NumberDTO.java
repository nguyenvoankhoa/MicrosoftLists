package com.view.data;
import com.model.column.ColumnType;
import com.model.datatype.Number;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberDTO {
    private double num;
    private byte[] icon;
    private ColumnType type;

    public NumberDTO(Number number) {
        this.num = number.getNum();
        this.icon = number.getIcon();
        this.type = number.getType();
    }
}

