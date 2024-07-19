package com.view;

import java.util.List;

public class BoardView extends View {
    List<Board> boards;

    public BoardView(String title, List<Board> boards) {
        super(title);
        setViewType(ViewType.BOARD);
        this.boards = boards;
    }
}
