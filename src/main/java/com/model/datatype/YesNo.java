package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Setter
@Getter
@SuperBuilder
public class YesNo extends BaseData implements IData<YesNo>, Comparator<YesNo> {
    private boolean isChosen;
    private final ColumnType type = ColumnType.YESNO;

    public YesNo(String colName, boolean isChosen) {
        super(colName);
        this.isChosen = isChosen;
    }

    @Override
    public YesNo getData() {
        return this;
    }

    @Override
    public void setData(YesNo data) {
        setChosen(data.isChosen());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.isChosen;
    }


    @Override
    public int compare(YesNo o1, YesNo o2) {
        return Comparator.comparing(YesNo::isChosen)
                .compare(o2, o1);
    }
}
