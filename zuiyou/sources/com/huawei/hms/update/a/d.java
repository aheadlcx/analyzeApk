package com.huawei.hms.update.a;

import com.huawei.hms.c.c;
import com.huawei.hms.support.log.a;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class d {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private int h = 0;

    d() {
    }

    public String a() {
        return this.b;
    }

    public int b() {
        try {
            return Integer.parseInt(this.c);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.h;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        stringBuilder.append("Name: ").append(this.a).append(", ");
        stringBuilder.append("File: ").append(this.b).append(", ");
        stringBuilder.append("Size: ").append(this.c).append(", ");
        stringBuilder.append("Hash: ").append(this.d).append(", ");
        stringBuilder.append("PackageName: ").append(this.e).append(", ");
        stringBuilder.append("PackageType: ").append(this.f).append(", ");
        stringBuilder.append("VersionName: ").append(this.g).append(", ");
        stringBuilder.append("VersionCode: ").append(this.h);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public static d a(String str) {
        Exception e;
        d dVar = new d();
        InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()));
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(byteArrayInputStream, "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    a(dVar, newPullParser);
                }
            }
            c.a(byteArrayInputStream);
        } catch (XmlPullParserException e2) {
            e = e2;
            try {
                a.d("FilelistResponse", "In parseResponse, Failed to parse xml for get-filelist response." + e.getMessage());
                dVar = new d();
                return dVar;
            } finally {
                c.a(byteArrayInputStream);
            }
        } catch (IOException e3) {
            e = e3;
            a.d("FilelistResponse", "In parseResponse, Failed to parse xml for get-filelist response." + e.getMessage());
            dVar = new d();
            return dVar;
        }
        return dVar;
    }

    private static void a(d dVar, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (xmlPullParser.getDepth() == 3 && "name".equals(name)) {
            dVar.a = xmlPullParser.nextText();
        }
        if (xmlPullParser.getDepth() != 4) {
            return;
        }
        if ("spath".equals(name)) {
            dVar.b = xmlPullParser.nextText();
        } else if ("size".equals(name)) {
            dVar.c = xmlPullParser.nextText();
        } else if ("sha256".equals(name)) {
            dVar.d = xmlPullParser.nextText();
        } else if ("packageName".equals(name)) {
            dVar.e = xmlPullParser.nextText();
        } else if ("packageType".equals(name)) {
            dVar.f = xmlPullParser.nextText();
        } else if ("versionName".equals(name)) {
            dVar.g = xmlPullParser.nextText();
        } else if ("versionCode".equals(name)) {
            dVar.h = b(xmlPullParser.nextText());
        }
    }

    private static int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
