package com.notify.util;

import java.text.SimpleDateFormat;

public class Date {
    public static String nowTimeTo(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new java.util.Date());
    }

}
