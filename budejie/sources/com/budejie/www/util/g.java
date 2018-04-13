package com.budejie.www.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.budejie.www.http.NetWorkUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    static final /* synthetic */ boolean b = (!g.class.desiredAssertionStatus());
    private static g c;
    private static File d;
    ArrayList<a> a;
    private List<a> e;
    private List<a> f;
    private Context g;
    private g$b h;
    private boolean i;

    public static class a {
        public int a;
        public String b;
        public String c;
        public File d;

        public String toString() {
            return "item:" + this.a + "-" + this.b + "-" + this.c;
        }

        public boolean equals(Object obj) {
            if ((obj instanceof a) && this.a == ((a) obj).a) {
                return true;
            }
            return false;
        }

        public static List<a> a(String str) {
            List<a> arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    a aVar = new a();
                    aVar.a = jSONObject.getInt("id");
                    aVar.b = jSONObject.getString("name");
                    aVar.c = jSONObject.getString("uri");
                    aVar.d = g.a(aVar.c);
                    arrayList.add(aVar);
                }
            } catch (JSONException e) {
                Log.i("BgMusicManager", "JSON 解析异常" + e.getMessage() + str);
            }
            return arrayList;
        }

        public static String a(List<a> list) {
            JSONArray jSONArray = new JSONArray();
            for (a aVar : list) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", aVar.a);
                    jSONObject.put("name", aVar.b);
                    jSONObject.put("uri", aVar.c);
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    Log.i("BgMusicManager", "JSONException " + e.getMessage() + " : " + aVar);
                    e.printStackTrace();
                }
            }
            return jSONArray.toString();
        }
    }

    public static g a(Context context) {
        if (c == null) {
            synchronized (g.class) {
                if (c == null) {
                    c = new g(context);
                }
            }
        }
        return c;
    }

    private g(Context context) {
        this.g = context;
        d = new File(Environment.getExternalStorageDirectory() + "/" + "android/data/com.budejie.www/bg_voice_cache/");
        if (!d.exists()) {
            d.mkdirs();
        }
        this.f = new ArrayList();
    }

    public void a() {
        if (this.f != null) {
            for (a aVar : this.f) {
                if (aVar.d.exists()) {
                    aVar.d.delete();
                }
            }
        }
    }

    public synchronized List<a> b() {
        List<a> f;
        if (this.a == null || this.a.isEmpty()) {
            f = f();
        } else {
            f = this.a;
        }
        return f;
    }

    public void c() {
        if (this.i && this.h != null) {
            this.h.b();
            try {
                this.h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void d() {
        if (!this.i && an.a(this.g)) {
            this.i = true;
            b bVar = new b(this.g.getApplicationContext(), new v(this.g));
            bVar.a(NetWorkUtil.a(this.g));
            net.tsz.afinal.a.b bVar2 = new net.tsz.afinal.a.b();
            bVar2.d("c", "voice");
            bVar2.d("a", "bglist");
            bVar.a("http://api.budejie.com/api/api_open.php", bVar2, new g$1(this));
        }
    }

    public void a(List<a> list) {
        if (list != null) {
            this.h = new g$b(this);
            this.e = list;
            this.h.start();
            return;
        }
        this.i = false;
        Log.i("BgMusicManager", "back ground music list download Fail");
    }

    public synchronized void e() {
        synchronized (this) {
            Editor edit = this.g.getSharedPreferences("BgMusicManager.JSON.CACHE", 0).edit();
            edit.putString("json", a.a(this.e));
            edit.commit();
        }
    }

    public synchronized List<a> f() {
        List arrayList;
        List<a> a = a.a(this.g.getSharedPreferences("BgMusicManager.JSON.CACHE", 0).getString("json", ""));
        if (b || a != null) {
            arrayList = new ArrayList();
            for (a aVar : a) {
                if (!(aVar.d == null || aVar.d.exists())) {
                    arrayList.add(aVar);
                }
            }
        } else {
            throw new AssertionError("BgMusicItem.parseJson can't be null");
        }
        return arrayList;
    }

    public static File a(String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            str2 = str.substring(7).replace("/", "-");
        }
        return new File(d, str2);
    }

    public synchronized void g() {
        List<a> f = f();
        e();
        for (a aVar : f) {
            if (!this.e.contains(aVar)) {
                this.f.add(aVar);
            }
        }
    }
}
