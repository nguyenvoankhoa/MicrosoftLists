package com.dto.column;
import com.model.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeColumnDTO extends BaseColumDTO{
    DateAndTime dateAndTime;
}

