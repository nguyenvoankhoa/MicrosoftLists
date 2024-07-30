package com.model.column;

import com.model.datatype.DateAndTime;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;


@Setter
@Getter
public class TimeColumn extends Column implements IColumn<DateAndTime> {
    private static final Logger LOGGER = Logger.getLogger(TimeColumn.class.getName());
    private DateAndTime dateAndTime;

    public TimeColumn(String name) {
        super(name);
        setType(ColumnType.DATE_AND_TIME);
    }

    @Override
    public DateAndTime getDefaultData() {
        return dateAndTime;
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public void checkConstraint(Object data) {
        Predicate<DateAndTime> requirePredicate = d -> !isRequire() || d != null;
        Common.checkValid(requirePredicate.test((DateAndTime) data));
    }

    @Override
    public DateAndTime createSimpleData(Object data) {
        if (data instanceof String dateString) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(dateString);
                return new DateAndTime(date);
            } catch (ParseException e) {
                LOGGER.log(Level.SEVERE, "Invalid date format: {}", dateString);
                return null;
            }
        } else if (data instanceof Date date) {
            return new DateAndTime(date);
        }
        return null;
    }
}
