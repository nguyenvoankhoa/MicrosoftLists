package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class YesNo implements Data<YesNo>{
    private boolean isChosen;
    @Override
    public YesNo getData() {
        return this;
    }

    @Override
    public void setData(YesNo data) {
        setChosen(data.isChosen());
    }
}
