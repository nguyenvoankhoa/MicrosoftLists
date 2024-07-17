package com;

import com.column.datatype.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Row {
    private List<Data> dataList;
    private List<Comment> comments;

    public Row() {
        dataList = new ArrayList<>();
        comments = new ArrayList<>();
    }
}
