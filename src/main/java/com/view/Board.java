package com.view;

import com.Row;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Board{
    String title;
    List<Row> rows;

    public Board(String title, List<Row> rows) {
        this.title = title;
        this.rows = rows;
    }
}