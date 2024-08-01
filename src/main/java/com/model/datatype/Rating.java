package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Data
@SuperBuilder
public class Rating extends BaseData implements IData<Rating>, Comparator<Rating> {
    private double rate;

    private int numRate;

    private final ColumnType type = ColumnType.AVERAGE_RATING;

    public Rating(String colName, double rate, int numRate) {
        super(colName);
        this.rate = rate;
        this.numRate = numRate;
    }

    public Rating(String colName, String... args) {
        super(colName);
        this.rate = Double.parseDouble(args[0]);
        this.numRate = Integer.parseInt(args[1]);
    }

    @Override
    public Rating getData() {
        return this;
    }

    @Override
    public void setData(Rating data) {
        setRate(data.getRate());
        setNumRate(data.getNumRate() + 1);
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.rate;
    }


    @Override
    public int compare(Rating o1, Rating o2) {
        return Comparator.comparing(Rating::getRate)
                .compare(o2, o1);
    }
}
