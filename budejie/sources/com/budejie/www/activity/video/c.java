package com.budejie.www.activity.video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class c {
    public static long a(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }
}
