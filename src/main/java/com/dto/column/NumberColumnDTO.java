package com.dto.column;

import com.dto.data.NumberDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberColumnDTO extends BaseColumDTO{
    NumberDTO number;
    double minVal;
    double maxVal;
}

