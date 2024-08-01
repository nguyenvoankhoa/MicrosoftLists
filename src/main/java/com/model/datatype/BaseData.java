package com.model.datatype;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseData {
    private String colName;

    public BaseData(String colName) {
        this.colName = colName;
    }
}
