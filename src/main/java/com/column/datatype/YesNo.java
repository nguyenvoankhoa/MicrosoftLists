package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Setter
@Getter
public class YesNo implements Data<YesNo>, Comparator<YesNo> {
    private boolean isChosen;

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
