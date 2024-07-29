package com.view.data;

import com.model.column.ColumnType;
import com.model.datatype.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDTO {
    private double rate;
    private ColumnType type;

    public RatingDTO(Rating rating) {
        this.rate = rating.getRate();
        this.type = rating.getType();
    }
}

