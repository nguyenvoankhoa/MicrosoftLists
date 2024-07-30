package com.dto.column;

import com.model.column.ColumnType;
import com.model.datatype.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageColumnDTO extends BaseColumDTO{
    private Image image;
    private ColumnType type;
}

