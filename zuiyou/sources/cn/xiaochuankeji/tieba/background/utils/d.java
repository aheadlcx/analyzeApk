package cn.xiaochuankeji.tieba.background.utils;

import cn.xiaochuankeji.tieba.background.net.XCError;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private static boolean a;
    private static String b;
    private static String c;

    private class a extends cn.xiaochuankeji.tieba.background.net.a {
        final /* synthetic */ d a;

        a(d dVar, String str, String str2, cn.xiaochuankeji.tieba.background.net.a.b<JSONObject> bVar, cn.xiaochuankeji.tieba.background.net.a.a aVar) {
            this.a = dVar;
            super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/stat/report_user_info"), d.c(str, str2), null, bVar, aVar);
        }
    }

    private static class b {
        private static d a = new d();
    }

    private d() {
        a = false;
    }

    public static d a() {
        return b.a;
    }

    public void a(String str, String str2) {
        b = str;
        c = str2;
    }

    public void b() {
        new a(this, b, c, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                com.izuiyou.a.a.b.c(jSONObject == null ? "Report Success" : jSONObject.toString());
                d.a = true;
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                com.izuiyou.a.a.b.e(xCError);
                d.a = true;
            }
        }).execute();
    }

    private static JSONObject c(String str, String str2) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ParamKey.LATITUDE, str);
            jSONObject.put(ParamKey.LONGITUDE, str2);
            b.put(RequestParameters.SUBRESOURCE_LOCATION, jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
