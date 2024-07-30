package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDTO {
    private double rate;
    private ColumnType type;

}
