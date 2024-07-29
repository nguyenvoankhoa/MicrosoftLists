package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextDTO {
    private String str;
    private ColumnType type;

    public TextDTO(Text text) {
        this.str = text.getStr();
        this.type = text.getType();
    }
}
