package com.view;

import com.column.datatype.DateAndTime;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CalendarView extends View {

    private CalendarLayout layout;

    private Map<DateAndTime, DateAndTime> dayBetween;

    public CalendarView(String title, Map<DateAndTime, DateAndTime> dayBetween) {
        super(title);
        setViewType(ViewType.CALENDAR);
        this.dayBetween = dayBetween;
    }
}
