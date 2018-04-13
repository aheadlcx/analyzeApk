package cn.xiaochuankeji.tieba.ui.mediabrowse.h5video;

import android.os.AsyncTask;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.d.f;
import cn.xiaochuankeji.tieba.d.g;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class a extends AsyncTask<Void, Void, String> {
    private final String a;
    private final a b;

    public interface a {
        void a(String str);
    }

    static class b extends DefaultHandler {
        private String a;

        b() {
        }

        public String a() {
            return this.a;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            this.a = attributes.getValue("src");
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }

    public a(String str, a aVar) {
        this.a = str;
        this.b = aVar;
    }

    protected String a(Void... voidArr) {
        HttpURLConnection a;
        IOException e;
        Throwable th;
        String str = null;
        try {
            a = g.a(new URL(this.a), null);
            try {
                if (a.getResponseCode() == 200) {
                    str = b(g.a(a));
                    g.b(a);
                } else {
                    g.b(a);
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    g.b(a);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    g.b(a);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            a = null;
            e.printStackTrace();
            g.b(a);
            return str;
        } catch (Throwable th3) {
            a = null;
            th = th3;
            g.b(a);
            throw th;
        }
        return str;
    }

    private String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("<video");
        int indexOf2 = str.indexOf("</video>", indexOf);
        if (indexOf < 0 || indexOf2 < 0) {
            return null;
        }
        String substring = str.substring(indexOf, indexOf2 + 8);
        Object bVar = new b();
        f.a(substring, bVar);
        return bVar.a();
    }

    protected void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }
}
