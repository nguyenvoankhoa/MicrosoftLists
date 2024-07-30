package com.dto.column;

import com.model.datatype.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextColumnDTO extends BaseColumDTO{
    Text text;
    int maxLength;
}

