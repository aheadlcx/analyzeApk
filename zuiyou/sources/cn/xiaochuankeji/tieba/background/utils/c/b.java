package cn.xiaochuankeji.tieba.background.utils.c;

import com.tencent.mm.opensdk.constants.ConstantsAPI.Token;
import org.json.JSONObject;

public class b {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    private boolean w = false;
    private boolean x = false;

    public boolean a() {
        return this.w;
    }

    public boolean b() {
        return this.x;
    }

    public void a(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject != null) {
            if (1 != jSONObject.optInt("share_abtesting")) {
                z = false;
            }
            this.w = z;
            JSONObject optJSONObject = jSONObject.optJSONObject("qq");
            if (optJSONObject != null) {
                this.a = optJSONObject.optString("share_post_title");
                this.b = optJSONObject.optString("share_post_desc");
            }
            optJSONObject = jSONObject.optJSONObject("weibo");
            if (optJSONObject != null) {
                this.i = optJSONObject.optString("share_post_title");
                this.j = optJSONObject.optString("share_post_desc");
            }
            optJSONObject = jSONObject.optJSONObject(Token.WX_TOKEN_PLATFORMID_VALUE);
            if (optJSONObject != null) {
                this.e = optJSONObject.optString("share_post_title");
                this.f = optJSONObject.optString("share_post_desc");
            }
            optJSONObject = jSONObject.optJSONObject("wechatCircle");
            if (optJSONObject != null) {
                this.g = optJSONObject.optString("share_post_title");
                this.h = optJSONObject.optString("share_post_desc");
            }
            optJSONObject = jSONObject.optJSONObject("qqzone");
            if (optJSONObject != null) {
                this.c = optJSONObject.optString("share_post_title");
                this.d = optJSONObject.optString("share_post_desc");
            }
        }
    }

    public void b(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject != null) {
            if (1 != jSONObject.optInt("share_abtesting")) {
                z = false;
            }
            this.x = z;
            JSONObject optJSONObject = jSONObject.optJSONObject("qq");
            if (optJSONObject != null) {
                this.k = optJSONObject.optString("share_review_title");
                this.l = optJSONObject.optString("share_review_desc");
            }
            optJSONObject = jSONObject.optJSONObject("weibo");
            if (optJSONObject != null) {
                this.s = optJSONObject.optString("share_review_title");
                this.t = optJSONObject.optString("share_review_desc");
            }
            optJSONObject = jSONObject.optJSONObject(Token.WX_TOKEN_PLATFORMID_VALUE);
            if (optJSONObject != null) {
                this.o = optJSONObject.optString("share_review_title");
                this.p = optJSONObject.optString("share_review_desc");
            }
            optJSONObject = jSONObject.optJSONObject("wechatCircle");
            if (optJSONObject != null) {
                this.q = optJSONObject.optString("share_review_title");
                this.r = optJSONObject.optString("share_review_desc");
            }
            optJSONObject = jSONObject.optJSONObject("qqzone");
            if (optJSONObject != null) {
                this.m = optJSONObject.optString("share_review_title");
                this.n = optJSONObject.optString("share_review_desc");
            }
        }
    }

    public void a(String str, String str2) {
        this.u = str;
        this.v = str2;
    }
}
