package com.view.column;
import com.model.column.ColumnType;
import com.model.datatype.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextColumnDTO extends BaseColumDTO{
    private Text text;
    private int maxLength;
    private ColumnType type;
}

