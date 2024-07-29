package com.view.column;
import com.model.column.ColumnType;
import com.model.column.TextColumn;
import com.model.datatype.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextColumnDTO {
    private Text text;
    private int maxLength;
    private ColumnType type;

    public TextColumnDTO(TextColumn column) {
        this.text = column.getDefaultData();
        this.maxLength = column.getMaxLength();
        this.type = column.getColumnType();
    }
}

