package com.model.view;

import com.model.Row;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardView extends View {
    List<Board> boards;

    public BoardView(String title, List<Board> boards) {
        super(title);
        setViewType(ViewType.BOARD);
        this.boards = boards;
    }

    public void moveRow(Board sBoard, Board eBoard, int rId, int cId) {
        Row row = sBoard.getRows().remove(rId);
        Object choice = row.getIDataList().get(cId).getData();
        row.getIDataList().get(cId).setData(choice);
        eBoard.getRows().add(row);
    }
}
