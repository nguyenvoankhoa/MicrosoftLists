package com.factory;

import com.model.Row;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.datatype.Choice;
import com.model.datatype.DateAndTime;
import com.model.view.*;
import com.util.Common;

import java.util.*;
import java.util.function.Function;

public class ViewFactory {
    private static final Map<ViewType, Function<Object[], View>> factory = new EnumMap<>(ViewType.class);
    private SmartList smartList;

    public ViewFactory(SmartList smartList) {
        this.smartList = smartList;
    }

    static {
        factory.put(ViewType.BOARD, params -> {
            ViewFactory vf = (ViewFactory) params[0];
            return vf.createBoardView((String) params[1], (IColumn) params[2]);
        });
        factory.put(ViewType.CALENDAR, params -> {
            ViewFactory vf = (ViewFactory) params[0];
            return vf.createCalendarView((String) params[1], (IColumn) params[2], (IColumn) params[3]);
        });
        factory.put(ViewType.LIST, params -> {
            ViewFactory vf = (ViewFactory) params[0];
            return vf.createListView((String) params[1]);
        });
        factory.put(ViewType.GALLERY, params -> {
            ViewFactory vf = (ViewFactory) params[0];
            return vf.createGalleryView((String) params[1]);
        });
    }

    public BoardView createBoardView(String title, IColumn column) {
        return Optional.of(column)
                .filter(col -> col.getColumnType().equals(ColumnType.CHOICE))
                .map(col -> {
                    Map<Object, List<Row>> choice = Common.groupBy(smartList, col.getName());
                    List<Board> boards = new ArrayList<>();
                    for (Map.Entry<Object, List<Row>> entry : choice.entrySet()) {
                        Choice key = (Choice) entry.getKey();
                        List<Row> value = entry.getValue();
                        boards.add(new Board(key.getName(), value));
                    }
                    return new BoardView(title, boards);
                })
                .orElse(null);
    }

    public CalendarView createCalendarView(String title, IColumn sDate, IColumn eDate) {
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

    public View createView(ViewType type, Object... params) {
        Function<Object[], View> viewCreator = factory.get(type);
        Object[] fullParams = new Object[params.length + 1];
        fullParams[0] = this;
        System.arraycopy(params, 0, fullParams, 1, params.length);
        return viewCreator.apply(fullParams);
    }
}
