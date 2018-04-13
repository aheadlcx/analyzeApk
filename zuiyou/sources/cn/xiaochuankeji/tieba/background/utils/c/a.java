package cn.xiaochuankeji.tieba.background.utils.c;

import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.background.h.c;
import cn.xiaochuankeji.tieba.ui.videomaker.i;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.j;

public class a implements cn.htjyb.netlib.d.a {
    private static a t;
    LinkedHashMap<String, String> a = new LinkedHashMap();
    LinkedHashMap<String, String> b = new LinkedHashMap();
    LinkedHashMap<String, String> c = new LinkedHashMap();
    LinkedHashMap<String, String> d = new LinkedHashMap();
    LinkedHashMap<String, String> e = new LinkedHashMap();
    ArrayList<c> f = new ArrayList();
    private int g = 2;
    private int h;
    private JSONObject i;
    private d j;
    private HashMap<String, Object> k = new HashMap();
    private int l;
    private String m;
    private String n;
    private b o;
    private String p;
    private HashMap<String, ArrayList<String>> q = new HashMap();
    private String r;
    private GrayConfigBean s = new GrayConfigBean();
    private int u = 12;
    private int v = 3600;
    private int w = 1;
    private int x;
    private int y = 0;
    private HashSet<a> z = new HashSet();

    public interface a {
        void a();
    }

    public boolean a() {
        return this.x == 1;
    }

    public boolean b() {
        return this.y == 1;
    }

    public static synchronized a c() {
        a aVar;
        synchronized (a.class) {
            if (t == null) {
                t = new a();
            }
            aVar = t;
        }
        return aVar;
    }

    private a() {
        H();
    }

    private void H() {
        JSONObject a = b.a(new File(J()), AppController.kDataCacheCharsetUTF8.name());
        if (a != null) {
            b(a);
        } else {
            b(b.a(new File(I()), AppController.kDataCacheCharset));
        }
        this.x = cn.xiaochuankeji.tieba.background.a.c().getInt("enable_tale", 0);
        this.y = cn.xiaochuankeji.tieba.background.a.c().getInt("enable_voice", 0);
    }

    private void a(JSONObject jSONObject) {
        b.a(jSONObject, new File(J()), AppController.kDataCacheCharsetUTF8.name());
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.h = jSONObject.optInt("version");
            this.i = jSONObject.optJSONObject("config");
        }
        if (this.i == null) {
            this.i = new JSONObject();
        }
        String optString = this.i.optString("inapp_domain");
        if (!TextUtils.isEmpty(optString)) {
            cn.xiaochuankeji.tieba.background.utils.d.a.c(optString);
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.e(this.i.optString("share_domain", "share.izuiyou.com"));
        this.l = this.i.optInt("assess_count");
        this.m = this.i.optString("android_jspatch_download_url");
        this.n = this.i.optString("android_jspatch_ver");
        this.r = this.i.optString("h5js_addr");
        this.w = this.i.optInt("total_https", 1);
        G();
        this.o = new b();
        this.o.a(this.i.optJSONObject("share_post_config_v2"));
        this.o.b(this.i.optJSONObject("share_review_config"));
        this.o.a(this.i.optString("share_topic_title"), this.i.optString("share_topic_desc"));
        this.p = this.i.optString("feedback_desc");
        this.q.clear();
        JSONObject optJSONObject = this.i.optJSONObject("https");
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                optString = (String) keys.next();
                JSONArray optJSONArray = optJSONObject.optJSONArray(optString);
                if (optJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        CharSequence optString2 = optJSONArray.optString(i);
                        if (!TextUtils.isEmpty(optString2)) {
                            arrayList.add(optString2);
                        }
                    }
                    this.q.put(optString, arrayList);
                }
            }
        }
        this.v = this.i.optInt("auto_refresh_time_interval", 3600);
        this.u = this.i.optInt("refresh_post_count", 12);
    }

    public void d() {
        new cn.xiaochuankeji.tieba.api.config.a().a().b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<GrayConfigBean>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((GrayConfigBean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.auth.a.a(new GrayConfigBean());
            }

            public void a(GrayConfigBean grayConfigBean) {
                if (grayConfigBean != null) {
                    this.a.s = grayConfigBean;
                    cn.xiaochuankeji.tieba.background.a.a().edit().putInt("tack_recommend_cache", grayConfigBean.recommendCacheTack).putInt("tack_auto_refresh", grayConfigBean.autoRefreshTack).putInt("tack_auto_refresh_more", grayConfigBean.autoRefreshMoreTack).apply();
                    cn.xiaochuankeji.tieba.background.a.c().edit().putInt("enable_tale", grayConfigBean.tale_enable).putInt("enable_voice", grayConfigBean.voice_enable).putInt("push_notification", grayConfigBean.push_notification_dialog).apply();
                    cn.xiaochuankeji.tieba.ui.auth.a.a(grayConfigBean);
                    this.a.a(grayConfigBean);
                }
            }
        });
        c.a().h();
    }

    private void a(GrayConfigBean grayConfigBean) {
        cn.xiaochuankeji.tieba.abmgr.a.c.a("ab_short_refresh_btn", grayConfigBean.ab_short_refresh_btn);
    }

    public b e() {
        return this.o;
    }

    public String f() {
        return this.p;
    }

    private String I() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "OnlineConfig.dat";
    }

    private String J() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "OnlineConfig_new.dat";
    }

    public String[] g() {
        String optString = this.i.optString("voiceimgid", "");
        if (optString.contains(",")) {
            return optString.split(",");
        }
        return new String[]{optString};
    }

    public long[] h() {
        int i = 0;
        JSONArray optJSONArray = this.i.optJSONArray("VoiceDefaultImages");
        long[] jArr;
        if (optJSONArray != null) {
            jArr = new long[optJSONArray.length()];
            while (i < optJSONArray.length()) {
                jArr[i] = (long) optJSONArray.optInt(i);
                i++;
            }
            return jArr;
        }
        String[] split = this.i.optString("voiceimgid", "").split(",");
        jArr = new long[split.length];
        while (i < split.length) {
            jArr[i] = Long.valueOf(split[i]).longValue();
            i++;
        }
        return jArr;
    }

    public void i() {
        if (this.j == null) {
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put("version", this.h);
                jSONObject.put("manufacturer", Build.MANUFACTURER);
            } catch (JSONException e) {
            }
            this.j = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/config/get"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
            this.j.b();
        }
    }

    public void j() {
        H();
    }

    public LinkedHashMap<String, String> k() {
        this.a.clear();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_user_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            this.a.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return this.a;
    }

    public boolean l() {
        if (-1 == this.i.optInt("http_dns", 0)) {
            return false;
        }
        return true;
    }

    public LinkedHashMap<String, String> m() {
        this.b.clear();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_post_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            this.b.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return this.b;
    }

    public LinkedHashMap<String, String> n() {
        this.e.clear();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("disgustpost_reasons_new");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            this.e.put(split[0], split[1]);
                        }
                    }
                }
            } else {
                this.e.put("1", "内容质量差");
                this.e.put("2", "屏蔽话题");
                this.e.put("3", "内容重复");
            }
        }
        return this.e;
    }

    public LinkedHashMap<String, String> o() {
        this.c.clear();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_review_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            this.c.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return this.c;
    }

    public boolean p() {
        return true;
    }

    public i q() {
        if (this.i != null) {
            JSONObject optJSONObject = this.i.optJSONObject("videomaker_config");
            if (optJSONObject != null) {
                return new i(optJSONObject);
            }
        }
        return null;
    }

    public String a(String str) {
        if (this.i != null) {
            return this.i.optString("campaign." + str + ".sharelink");
        }
        return null;
    }

    public LinkedHashMap<String, String> r() {
        this.d.clear();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_delpost_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            this.d.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return this.d;
    }

    public ArrayList<c> s() {
        this.f.clear();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_ugcvideo_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        c cVar = new c();
                        cVar.a = optJSONObject.optInt("id");
                        cVar.b = optJSONObject.optString("title");
                        cVar.c = optJSONObject.optString("imgurl");
                        this.f.add(cVar);
                    }
                }
            }
        }
        return this.f;
    }

    public LinkedHashMap<String, String> t() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_danmaku_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            linkedHashMap.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> u() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_chat_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            linkedHashMap.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> v() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_flow_xroom_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            linkedHashMap.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> w() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        if (this.i != null) {
            JSONArray optJSONArray = this.i.optJSONArray("report_flow_xmsg_reasons");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String str = (String) optJSONArray.opt(i);
                    if (str != null) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            linkedHashMap.put(split[0], split[1]);
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public ArrayList<String> b(String str) {
        return (ArrayList) this.q.get(str);
    }

    public void onTaskFinish(d dVar) {
        this.j = null;
        JSONObject jSONObject = dVar.c.c;
        if (dVar.c.a && jSONObject != null && jSONObject.optJSONObject("config") != null) {
            b(jSONObject);
            a(jSONObject);
            if (this.z != null && !this.z.isEmpty()) {
                Iterator it = this.z.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a();
                }
            }
        }
    }

    public int x() {
        return this.l;
    }

    public int y() {
        return this.v;
    }

    public int z() {
        return this.u;
    }

    public String A() {
        if (this.i != null) {
            return this.i.optString("h5parse_desc");
        }
        return null;
    }

    public String B() {
        return this.r;
    }

    @Nullable
    public GrayConfigBean C() {
        return this.s;
    }

    public void D() {
        this.w = 0;
        G();
    }

    public void E() {
        this.w = 1;
        G();
    }

    public boolean F() {
        return this.w == 1;
    }

    public void G() {
        String str = "https";
        if (!F()) {
            str = "http";
        }
        cn.xiaochuankeji.tieba.network.c.a(str + "://" + cn.xiaochuankeji.tieba.background.utils.d.a.e(), AppController.instance().buildHttpClient(false), new cn.xiaochuankeji.tieba.network.custom.b(), new cn.xiaochuankeji.tieba.network.custom.c());
    }
}
