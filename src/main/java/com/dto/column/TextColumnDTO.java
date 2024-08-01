package com.dto.column;

import com.dto.data.TextDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextColumnDTO extends BaseColumDTO{
    TextDTO text;
    int maxLength;
}

