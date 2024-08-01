package com.dto.column;

import com.dto.data.YesNoDTO;
import com.model.datatype.YesNo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoColumnDTO extends BaseColumDTO {
    YesNoDTO data;
}
