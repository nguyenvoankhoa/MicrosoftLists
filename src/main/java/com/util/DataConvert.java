package com.util;


import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DataConvert {
    private static final Logger LOGGER = Logger.getLogger(DataConvert.class.getName());

    public Date convertStringToDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Invalid date format: {}", str);
            return null;
        }
    }
}
