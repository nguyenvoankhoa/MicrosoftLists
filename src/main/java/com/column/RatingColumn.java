package com.column;

import com.column.datatype.Rating;

public class RatingColumn extends Column implements IColumn {
    private Rating rating;

    @Override
    public Object getDefaultData() {
        return rating;
    }

    @Override
    public ColumnType getType() {
        return ColumnType.AVERAGE_RATING;
    }
}
