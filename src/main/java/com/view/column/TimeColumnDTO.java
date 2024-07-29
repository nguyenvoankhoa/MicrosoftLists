package com.view.column;
import com.model.column.ColumnType;
import com.model.column.TimeColumn;
import com.model.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeColumnDTO {
    private DateAndTime dateAndTime;
    private ColumnType type;

    public TimeColumnDTO(TimeColumn column) {
        this.dateAndTime = column.getDefaultData();
        this.type = column.getColumnType();
    }
}

