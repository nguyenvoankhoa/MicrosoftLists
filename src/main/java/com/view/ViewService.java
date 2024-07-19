package com.view;

import com.Common;
import com.Row;
import com.SmartList;
import com.column.IColumn;
import com.column.datatype.Choice;
import com.column.datatype.DateAndTime;

import java.util.*;

public class ViewService implements IViewService {
    private SmartList smartList;

    public ViewService(SmartList smartList) {
        this.smartList = smartList;
    }

    public BoardView createBoardView(IColumn column, String title) {
        Map<Object, List<Row>> choice = Common.groupBy(smartList, column.getName());
        List<Board> boards = new ArrayList<>();
        for (Map.Entry<Object, List<Row>> entry : choice.entrySet()) {
            Choice key = (Choice) entry.getKey();
            List<Row> value = entry.getValue();
            boards.add(new Board(key.getName(), value));
        }
        return new BoardView(title, boards);
    }

    public CalendarView createCalendarView(IColumn sDate, IColumn eDate, String title) {
        Map<DateAndTime, DateAndTime> dateBetween = new HashMap<>();
        int sCol = Common.getColumnIndex(smartList, sDate);
        int eCol = Common.getColumnIndex(smartList, eDate);
        for (Row row : smartList.getRows()) {
            dateBetween.put((DateAndTime) row.getIDataList().get(sCol),
                    (DateAndTime) row.getIDataList().get(eCol));
        }
        return new CalendarView(title, dateBetween);
    }

    public ListView createListView(String title) {
        return new ListView(title, smartList);
    }

    public GalleryView createGalleryView(String title) {
        List<Card> cards = smartList.getRows()
                .stream()
                .flatMap(row -> row.getIDataList().stream().map(Card::new))
                .toList();
        return new GalleryView(title, cards);
    }

}
