package com.view.column;
import com.model.column.ColumnType;
import com.model.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeColumnDTO extends BaseColumDTO{
    private DateAndTime dateAndTime;
    private ColumnType type;
}

