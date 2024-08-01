package com.dto.column;

import com.dto.data.RatingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingColumnDTO extends BaseColumDTO{
    RatingDTO rating;
    double maxRate;
    double minRate;
}

