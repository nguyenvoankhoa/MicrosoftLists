package com.view.column;

import com.model.column.ColumnType;
import com.model.column.RatingColumn;
import com.model.datatype.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingColumnDTO {
    private Rating rating;
    private ColumnType type;

    public RatingColumnDTO(RatingColumn column) {
        this.rating = column.getDefaultData();
        this.type = column.getColumnType();
    }
}

