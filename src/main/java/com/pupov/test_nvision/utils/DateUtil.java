package com.pupov.test_nvision.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final Logger logger =LoggerFactory.getLogger(DateUtil.class);
    private static final String DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);

    public static Date getDateOfString(String dateStr) {
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Error parse date", e);
        }
        return new Date();
    }

    public static String getStringOfDate(Date date) {
        return simpleDateFormat.format(date);
    }
}
