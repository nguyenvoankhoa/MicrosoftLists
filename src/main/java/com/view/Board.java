package com.view;

import com.Row;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Board {
    String title;
    List<Row> iDataList;

    public Board(String title, List<Row> iDataList) {
        this.title = title;
        this.iDataList = iDataList;
    }
}
