package org.apache.commons.cli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class g {
    public static Object a(String str, Object obj) throws ParseException {
        return a(str, (Class) obj);
    }

    public static Object a(String str, Class cls) throws ParseException {
        if (f.a == cls) {
            return str;
        }
        if (f.b == cls) {
            return a(str);
        }
        if (f.c == cls) {
            return b(str);
        }
        if (f.d == cls) {
            return d(str);
        }
        if (f.e == cls) {
            return c(str);
        }
        if (f.g == cls) {
            return f(str);
        }
        if (f.f == cls) {
            return f(str);
        }
        if (f.h == cls) {
            return g(str);
        }
        if (f.i == cls) {
            return e(str);
        }
        return null;
    }

    public static Object a(String str) throws ParseException {
        try {
            try {
                return Class.forName(str).newInstance();
            } catch (Exception e) {
                throw new ParseException(new StringBuffer().append(e.getClass().getName()).append("; Unable to create an instance of: ").append(str).toString());
            }
        } catch (ClassNotFoundException e2) {
            throw new ParseException(new StringBuffer().append("Unable to find the class: ").append(str).toString());
        }
    }

    public static Number b(String str) throws ParseException {
        try {
            if (str.indexOf(46) != -1) {
                return Double.valueOf(str);
            }
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public static Class c(String str) throws ParseException {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new ParseException(new StringBuffer().append("Unable to find the class: ").append(str).toString());
        }
    }

    public static Date d(String str) throws ParseException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static URL e(String str) throws ParseException {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new ParseException(new StringBuffer().append("Unable to parse the URL: ").append(str).toString());
        }
    }

    public static File f(String str) throws ParseException {
        return new File(str);
    }

    public static File[] g(String str) throws ParseException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
