package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import cn.xiaochuankeji.tieba.api.log.a;
import cn.xiaochuankeji.tieba.d.g;
import cn.xiaochuankeji.tieba.network.e;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import com.qq.e.comm.constants.Constants.KEYS;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import rx.d;
import rx.d$a;
import rx.j;

public class h {
    public String a;
    public boolean b = false;
    public int c = 0;
    private String d = UUID.randomUUID().toString();
    private JSONObject e = new JSONObject();
    private JSONObject f = new JSONObject();
    private JSONObject g;
    private a h;
    private boolean i = false;
    private long j;
    private long k = 0;
    private long l = 0;
    private boolean m = true;
    private Map<String, JSONArray> n = new HashMap();
    private Map<String, JSONArray> o = new HashMap();
    private Map<String, JSONObject> p = new HashMap();

    public h() {
        this.f.put("token", this.d);
    }

    public void a(boolean z, String str) {
        this.b = z;
        this.a = str;
        if (!this.n.containsKey(str)) {
            this.n.put(str, new JSONArray());
            this.o.put(str, new JSONArray());
        }
    }

    public boolean a(String str) {
        return this.n.containsKey(str);
    }

    public void a(long j, long j2, long j3, String str) {
        final String str2 = str;
        final long j4 = j;
        final long j5 = j2;
        final long j6 = j3;
        d.a(new d$a<Void>(this) {
            final /* synthetic */ h e;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Void> jVar) {
                g.a a = g.a();
                String b = g.b();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("c", Integer.valueOf(100100));
                jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
                this.e.g = new JSONObject();
                this.e.g.put(KEYS.BIZ, str2);
                this.e.g.put("pid", Long.valueOf(j4));
                if (j5 > 0) {
                    this.e.g.put("rid", Long.valueOf(j5));
                }
                this.e.g.put(SpeechConstant.ISV_VID, Long.valueOf(j6));
                this.e.g.put("ip", a.b);
                this.e.g.put("net", a.c);
                this.e.g.put("isp", b);
                jSONObject.put("p", this.e.g);
                this.e.e.put("100", jSONObject);
                jVar.onCompleted();
            }
        }).a(rx.f.a.c()).b(new j<Void>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Void voidR) {
            }
        });
    }

    public void a(String str, String str2, long j, String str3, int i, String str4, boolean z) {
        Log.d("mingling", "onHostSelected:" + this.a);
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        jSONObject.put("c", Integer.valueOf(100200));
        jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
        final JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(SpeechConstant.DOMAIN, Uri.parse(str2).getHost());
        jSONObject2.put("url", str2);
        jSONObject2.put("type", TextUtils.isEmpty(str3) ? "localdns" : "smartdns");
        jSONObject2.put("ip", str3);
        jSONObject2.put(Statics.TIME, Long.valueOf(j));
        jSONObject2.put("code", Integer.valueOf(i));
        jSONObject2.put("reason", str4);
        jSONObject2.put("cache-header", Boolean.valueOf(z));
        if (!this.m) {
            jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, Integer.valueOf(2));
        } else if (i >= 200 && i < 400) {
            jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, Integer.valueOf(1));
        }
        jSONObject.put("p", jSONObject2);
        JSONObject jSONObject3 = (JSONObject) this.p.get(str);
        if (jSONObject3 != null) {
            jSONObject3.put("200", jSONObject);
        } else {
            jSONObject3 = new JSONObject();
            jSONObject3.put("200", jSONObject);
            this.p.put(str, jSONObject3);
        }
        this.a = str2;
        cn.xiaochuankeji.tieba.background.a.p().d().execute(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                try {
                    try {
                        InetAddress byName = InetAddress.getByName(new URL(this.b.a).getHost());
                        CharSequence hostAddress = byName != null ? byName.getHostAddress() : "";
                        if (TextUtils.isEmpty(hostAddress)) {
                            jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, "2");
                            jSONObject2.put("reason", "ip is empty");
                            return;
                        }
                        jSONObject2.put("ip", hostAddress);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, "2");
                        jSONObject2.put("reason", "UnknownHostException");
                    } catch (SecurityException e2) {
                        jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, "2");
                        jSONObject2.put("reason", "SecurityException");
                    }
                } catch (MalformedURLException e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    public void a() {
        this.c++;
        this.i = true;
        this.j = System.currentTimeMillis();
    }

    public void a(String str, int i, float f, String str2) {
        if (!this.m) {
            try {
                if (this.i) {
                    this.i = false;
                    long currentTimeMillis = System.currentTimeMillis() - this.j;
                    if (currentTimeMillis > this.k) {
                        this.k = currentTimeMillis;
                    }
                    this.l = currentTimeMillis + this.l;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("c", Integer.valueOf(100300));
                jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("url", this.a);
                jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, Integer.valueOf(i));
                jSONObject2.put("offset", Integer.valueOf((int) f));
                jSONObject2.put("reason", str2);
                jSONObject.put("p", jSONObject2);
                JSONArray jSONArray = (JSONArray) this.o.get(str);
                if (jSONArray != null && jSONArray.size() < 4) {
                    jSONArray.add(jSONObject);
                } else if (jSONArray == null) {
                    jSONArray = new JSONArray();
                    jSONArray.add(jSONObject);
                    this.o.put(str, jSONArray);
                }
            } catch (Exception e) {
            }
        }
    }

    public void a(@NonNull String str, @NonNull int i, @NonNull String str2, @Nullable String str3, @Nullable int i2, @NonNull long j, @Nullable String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("c", Integer.valueOf(100300));
            jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("state", Integer.valueOf(i));
            jSONObject2.put("host", str2);
            jSONObject2.put("url", str);
            jSONObject2.put("ip", str3);
            jSONObject2.put("type", TextUtils.isEmpty(str3) ? "localdns" : "smartdns");
            jSONObject2.put("code", Integer.valueOf(i2));
            jSONObject2.put("offset", Long.valueOf(j));
            jSONObject2.put("reason", str4);
            jSONObject.put("p", jSONObject2);
            JSONArray jSONArray = (JSONArray) this.o.get(str);
            if (jSONArray != null) {
                Object obj = null;
                int i3 = 0;
                while (i3 < jSONArray.size()) {
                    try {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i3).getJSONObject("p");
                        if (jSONObject3 != null && jSONObject3.getIntValue("state") == i && jSONObject3.getString("type").equals(jSONObject2.getString("type")) && jSONObject3.getString("host").equals(str2)) {
                            obj = 1;
                            break;
                        }
                        i3++;
                    } catch (NullPointerException e) {
                        obj = null;
                    }
                }
                if (obj == null) {
                    jSONArray.add(jSONObject);
                    return;
                }
                return;
            }
            jSONArray = new JSONArray();
            jSONArray.add(jSONObject);
            this.o.put(str, jSONArray);
        } catch (Exception e2) {
        }
    }

    public void a(String str, int i, long j, long j2, float f, String str2) {
        JSONObject jSONObject;
        JSONArray jSONArray = (JSONArray) this.o.get(str);
        if (!(jSONArray == null || jSONArray.size() <= 0 || this.n == null)) {
            jSONObject = (JSONObject) this.p.get(str);
            if (jSONObject != null) {
                jSONObject.put("300", jSONArray);
            }
        }
        if (f <= 0.0f) {
            f = 0.0f;
        }
        try {
            jSONObject = new JSONObject();
            jSONObject.put("c", Integer.valueOf(100400));
            jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("file", Boolean.valueOf(this.b));
            jSONObject2.put("url", this.a);
            jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, Integer.valueOf(i));
            jSONObject2.put("dur", Long.valueOf(j2));
            jSONObject2.put("dowloaded", Float.valueOf(f));
            jSONObject2.put("play_ts", Long.valueOf(j));
            jSONObject2.put("lagcnt", Integer.valueOf(this.c));
            jSONObject2.put("reason", str2);
            jSONObject2.put("maxLagTime", Long.valueOf(this.k));
            jSONObject2.put("totalLagTime", Long.valueOf(this.l));
            jSONObject.put("p", jSONObject2);
            jSONObject2 = (JSONObject) this.p.get(str);
            if (jSONObject2 != null) {
                jSONObject2.put("400", jSONObject);
                return;
            }
            jSONObject2 = new JSONObject();
            jSONObject2.put("400", jSONObject);
            this.p.put(str, jSONObject2);
        } catch (Exception e) {
        }
    }

    public void a(String str, String str2, long j) {
        try {
            JSONObject jSONObject;
            JSONArray jSONArray = (JSONArray) this.o.get(str);
            if (!(jSONArray == null || jSONArray.size() <= 0 || this.n == null)) {
                jSONObject = (JSONObject) this.p.get(str);
                if (!(jSONObject == null || jSONObject.containsKey("300"))) {
                    jSONObject.put("300", jSONArray);
                }
            }
            jSONObject = new JSONObject();
            jSONObject.put("c", Integer.valueOf(100500));
            jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("url", str2);
            jSONObject2.put("offset", Long.valueOf(j));
            jSONObject.put("p", jSONObject2);
            jSONObject2 = (JSONObject) this.p.get(str);
            if (jSONObject2 != null) {
                jSONObject2.put("500", jSONObject);
                return;
            }
            jSONObject2 = new JSONObject();
            jSONObject2.put("500", jSONObject);
            this.p.put(str, jSONObject2);
        } catch (Exception e) {
        }
    }

    public void a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("c", Integer.valueOf(100600));
            jSONObject.put("t", Long.valueOf(System.currentTimeMillis()));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(SpeechUtility.TAG_RESOURCE_RESULT, Integer.valueOf(i));
            jSONObject.put("p", jSONObject2);
            this.e.put("600", jSONObject);
        } catch (Exception e) {
        }
    }

    public void b() {
        if (e.a().g()) {
            this.h = new a();
            if (this.p.size() > 0) {
                try {
                    this.e.put("200~500", this.p.values());
                    this.f.put("video_stats", this.e);
                    this.h.a(this.f).b(rx.f.a.c()).b(new j<Void>(this) {
                        final /* synthetic */ h a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((Void) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                        }

                        public void a(Void voidR) {
                        }
                    });
                } catch (Exception e) {
                }
            }
        }
    }
}
