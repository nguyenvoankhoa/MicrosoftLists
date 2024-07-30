package com.dto.column;

import com.model.datatype.YesNo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumnDTO extends BaseColumDTO {
    YesNo data;
}
