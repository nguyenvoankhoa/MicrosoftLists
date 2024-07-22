package com.column.datatype;

import com.column.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class YesNo implements IData<YesNo>, Comparator<YesNo> {
    private boolean isChosen;
    private ColumnType type = ColumnType.YESNO;

    public YesNo(boolean isChosen) {
        this.isChosen = isChosen;
    }

    @Override
    public YesNo getData() {
        return this;
    }

    @Override
    public void setData(YesNo data) {
        setChosen(data.isChosen());
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
