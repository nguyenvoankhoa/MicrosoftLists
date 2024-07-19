package com.view;

import com.column.IColumn;

public interface IViewService {
    BoardView createBoardView(IColumn column, String title);
    CalendarView createCalendarView(IColumn sDate, IColumn eDate, String title);

}
