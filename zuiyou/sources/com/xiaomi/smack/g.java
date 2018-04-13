package com.xiaomi.smack;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public final class g {
    private static int a;
    private static int b;
    private static int c = 300000;
    private static int d = 330000;
    private static Vector<String> e = new Vector();

    static {
        a = 5000;
        b = 330000;
        try {
            for (ClassLoader resources : d()) {
                Enumeration resources2 = resources.getResources("META-INF/smack-config.xml");
                while (resources2.hasMoreElements()) {
                    InputStream inputStream = null;
                    inputStream = ((URL) resources2.nextElement()).openStream();
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(inputStream, "UTF-8");
                    int eventType = newPullParser.getEventType();
                    do {
                        if (eventType == 2) {
                            if (newPullParser.getName().equals("className")) {
                                a(newPullParser);
                            } else {
                                try {
                                    if (newPullParser.getName().equals("packetReplyTimeout")) {
                                        a = a(newPullParser, a);
                                    } else if (newPullParser.getName().equals("keepAliveInterval")) {
                                        b = a(newPullParser, b);
                                    } else if (newPullParser.getName().equals("mechName")) {
                                        e.add(newPullParser.nextText());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    try {
                                        inputStream.close();
                                    } catch (Exception e2) {
                                    }
                                } catch (Throwable th) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e3) {
                                    }
                                    throw th;
                                }
                            }
                        }
                        eventType = newPullParser.next();
                    } while (eventType != 1);
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                    }
                }
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    private g() {
    }

    private static int a(XmlPullParser xmlPullParser, int i) {
        try {
            i = Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String a() {
        return "3.1.0";
    }

    private static void a(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException e) {
            System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + nextText);
        }
    }

    public static int b() {
        return b;
    }

    public static int c() {
        return c;
    }

    private static ClassLoader[] d() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{g.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        List arrayList = new ArrayList();
        int length = classLoaderArr.length;
        while (i < length) {
            Object obj = classLoaderArr[i];
            if (obj != null) {
                arrayList.add(obj);
            }
            i++;
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }
}
