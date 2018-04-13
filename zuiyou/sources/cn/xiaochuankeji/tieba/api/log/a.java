package cn.xiaochuankeji.tieba.api.log;

import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.tencent.tauth.AuthActivity;
import java.io.File;
import java.io.IOException;
import okhttp3.u;
import okhttp3.v;
import okhttp3.z;
import rx.d;

public class a {
    private LogService a = ((LogService) c.a().c(LogService.class));

    public d<Void> a(JSONObject jSONObject) {
        return this.a.sendVideoDiagnosis(jSONObject);
    }

    public d<Void> a(JSONObject jSONObject, File file) throws IOException {
        z create = z.create(u.a(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE), b.c(file));
        okhttp3.v.a a = new okhttp3.v.a().a(v.e);
        a.a("json", jSONObject.toJSONString());
        a.a("file", file.getName(), create);
        return this.a.sendErrorDiagnosis(create);
    }

    public d<Void> b(JSONObject jSONObject) {
        return this.a.sendPicHttpStatReporter(jSONObject);
    }

    public d<Void> c(JSONObject jSONObject) {
        return this.a.sendPostDurStatReporter(jSONObject);
    }

    public d<Void> a(String str, String str2, String str3, long j, long j2, JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AuthActivity.ACTION_KEY, str);
            jSONObject2.put("otype", str2);
            jSONObject2.put("src", str3);
            if (j != 0) {
                jSONObject2.put("id", Long.valueOf(j));
            }
            if (j2 != 0) {
                jSONObject2.put("oid", Long.valueOf(j2));
            }
            jSONObject2.put("data", jSONObject);
            jSONArray2.add(jSONObject2);
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("list", jSONArray2);
        return this.a.sendActionLog(jSONObject3);
    }
}
