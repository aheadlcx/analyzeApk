package com.budejie.www.activity.video.barrage.danmaku.a.a;

import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.activity.video.barrage.danmaku.model.android.c;
import com.budejie.www.activity.video.barrage.danmaku.model.f;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.budejie.www.activity.video.barrage.danmaku.model.l;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class b extends com.budejie.www.activity.video.barrage.danmaku.a.a {
    private float i;
    private float j;

    public class a extends DefaultHandler {
        public c a = null;
        public com.budejie.www.activity.video.barrage.danmaku.model.c b = null;
        public boolean c = false;
        public int d = 0;
        final /* synthetic */ b e;

        public a(b bVar) {
            this.e = bVar;
        }

        public c a() {
            return this.a;
        }

        public void startDocument() throws SAXException {
            this.a = new c();
        }

        public void endDocument() throws SAXException {
            this.c = true;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            int i = -16777216;
            if (str2.length() == 0) {
                str2 = str3;
            }
            if (str2.toLowerCase(Locale.getDefault()).trim().equals("d")) {
                String[] split = attributes.getValue("p").split(",");
                if (split.length > 0) {
                    long parseFloat = (long) (Float.parseFloat(split[0]) * 1000.0f);
                    int parseInt = Integer.parseInt(split[1]);
                    float parseFloat2 = Float.parseFloat(split[2]);
                    int parseInt2 = Integer.parseInt(split[3]) | -16777216;
                    this.b = this.e.h.t.a(parseInt, this.e.h);
                    if (this.b != null) {
                        this.b.b = parseFloat;
                        this.b.j = (this.e.e - 0.6f) * parseFloat2;
                        this.b.e = parseInt2;
                        com.budejie.www.activity.video.barrage.danmaku.model.c cVar = this.b;
                        if (parseInt2 <= -16777216) {
                            i = -1;
                        }
                        cVar.h = i;
                    }
                }
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            if (this.b != null) {
                if (this.b.p != null) {
                    if (str2.length() == 0) {
                        str2 = str3;
                    }
                    if (str2.equalsIgnoreCase("d")) {
                        this.b.a(this.e.b);
                        this.a.a(this.b);
                    }
                }
                this.b = null;
            }
        }

        public void characters(char[] cArr, int i, int i2) {
            if (this.b != null) {
                com.budejie.www.activity.video.barrage.danmaku.c.b.a(this.b, a(new String(cArr, i, i2)));
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar = this.b;
                int i3 = this.d;
                this.d = i3 + 1;
                cVar.q = i3;
                String trim = String.valueOf(this.b.c).trim();
                if (this.b.n() == 7 && trim.startsWith("[") && trim.endsWith("]")) {
                    CharSequence[] charSequenceArr;
                    try {
                        JSONArray jSONArray = new JSONArray(trim);
                        String[] strArr = new String[jSONArray.length()];
                        for (int i4 = 0; i4 < strArr.length; i4++) {
                            strArr[i4] = jSONArray.getString(i4);
                        }
                        charSequenceArr = strArr;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        charSequenceArr = null;
                    }
                    if (charSequenceArr == null || charSequenceArr.length < 5) {
                        this.b = null;
                        return;
                    }
                    int parseFloat;
                    float f;
                    float f2;
                    long j;
                    this.b.c = charSequenceArr[4];
                    float parseFloat2 = Float.parseFloat(charSequenceArr[0]);
                    float parseFloat3 = Float.parseFloat(charSequenceArr[1]);
                    String[] split = charSequenceArr[2].split("-");
                    int parseFloat4 = (int) (((float) com.budejie.www.activity.video.barrage.danmaku.model.b.a) * Float.parseFloat(split[0]));
                    if (split.length > 1) {
                        parseFloat = (int) (Float.parseFloat(split[1]) * ((float) com.budejie.www.activity.video.barrage.danmaku.model.b.a));
                    } else {
                        parseFloat = parseFloat4;
                    }
                    long parseFloat5 = (long) (Float.parseFloat(charSequenceArr[3]) * 1000.0f);
                    long j2 = 0;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    if (charSequenceArr.length >= 7) {
                        f4 = Float.parseFloat(charSequenceArr[5]);
                        f3 = Float.parseFloat(charSequenceArr[6]);
                    }
                    if (charSequenceArr.length >= 11) {
                        long j3;
                        float parseFloat6 = Float.parseFloat(charSequenceArr[7]);
                        float parseFloat7 = Float.parseFloat(charSequenceArr[8]);
                        if ("".equals(charSequenceArr[9])) {
                            j3 = parseFloat5;
                        } else {
                            j3 = (long) Integer.parseInt(charSequenceArr[9]);
                        }
                        long j4;
                        if ("".equals(charSequenceArr[10])) {
                            j4 = j3;
                            f = parseFloat7;
                            f2 = parseFloat6;
                            j = j4;
                        } else {
                            j2 = (long) Float.parseFloat(charSequenceArr[10]);
                            j4 = j3;
                            f = parseFloat7;
                            f2 = parseFloat6;
                            j = j4;
                        }
                    } else {
                        j = parseFloat5;
                        f = parseFloat3;
                        f2 = parseFloat2;
                    }
                    this.b.p = new f(parseFloat5);
                    this.b.f = f4;
                    this.b.g = f3;
                    this.e.h.t.a(this.b, parseFloat2, parseFloat3, f2, f, j, j2, this.e.i, this.e.j);
                    this.e.h.t.a(this.b, parseFloat4, parseFloat, parseFloat5);
                    if (charSequenceArr.length >= 12 && !TextUtils.isEmpty(charSequenceArr[11]) && Constants.SERVICE_SCOPE_FLAG_VALUE.equals(charSequenceArr[11])) {
                        this.b.h = 0;
                    }
                    if (charSequenceArr.length >= 13) {
                    }
                    if (charSequenceArr.length >= 14) {
                    }
                    if (charSequenceArr.length >= 15 && !"".equals(charSequenceArr[14])) {
                        String[] split2 = charSequenceArr[14].substring(1).split("L");
                        if (split2 != null && split2.length > 0) {
                            float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{split2.length, 2});
                            for (i3 = 0; i3 < split2.length; i3++) {
                                String[] split3 = split2[i3].split(",");
                                fArr[i3][0] = Float.parseFloat(split3[0]);
                                fArr[i3][1] = Float.parseFloat(split3[1]);
                            }
                            com.budejie.www.activity.video.barrage.danmaku.a.b.a(this.b, fArr, this.e.i, this.e.j);
                        }
                    }
                }
            }
        }

        private String a(String str) {
            if (str.contains("&amp;")) {
                str = str.replace("&amp;", "&");
            }
            if (str.contains("&quot;")) {
                str = str.replace("&quot;", "\"");
            }
            if (str.contains("&gt;")) {
                str = str.replace("&gt;", ">");
            }
            if (str.contains("&lt;")) {
                return str.replace("&lt;", "<");
            }
            return str;
        }
    }

    public /* synthetic */ k e() {
        return g();
    }

    static {
        System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
    }

    public c g() {
        if (this.a != null) {
            a aVar = (a) this.a;
            try {
                XMLReader createXMLReader = XMLReaderFactory.createXMLReader();
                Object aVar2 = new a(this);
                createXMLReader.setContentHandler(aVar2);
                createXMLReader.parse(new InputSource(aVar.b()));
                return aVar2.a();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public com.budejie.www.activity.video.barrage.danmaku.a.a a(l lVar) {
        super.a(lVar);
        this.i = ((float) this.c) / 682.0f;
        this.j = ((float) this.d) / 438.0f;
        return this;
    }
}
