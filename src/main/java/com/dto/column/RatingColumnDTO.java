package com.dto.column;

import com.model.column.ColumnType;
import com.model.datatype.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingColumnDTO extends BaseColumDTO{
    private Rating rating;
    private ColumnType type;
}

