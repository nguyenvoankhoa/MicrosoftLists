package com.dto.column;

import com.model.datatype.Number;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumnDTO extends BaseColumDTO{
    Number number;
    double minVal;
    double maxVal;
}

