package com;

import com.column.datatype.IData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Row {
    private List<IData> iDataList;
    private List<Comment> comments;

    public Row() {
        iDataList = new ArrayList<>();
        comments = new ArrayList<>();
    }
}
