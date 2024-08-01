package com.dto.column;
import com.dto.data.DateAndTimeDTO;
import com.model.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeColumnDTO extends BaseColumDTO{
    DateAndTimeDTO dateAndTime;
}

