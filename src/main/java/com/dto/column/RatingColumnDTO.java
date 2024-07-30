package com.dto.column;

import com.model.datatype.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingColumnDTO extends BaseColumDTO{
    Rating rating;
    double maxRate;
    double minRate;
}

