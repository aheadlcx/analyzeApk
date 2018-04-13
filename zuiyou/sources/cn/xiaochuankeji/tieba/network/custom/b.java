package cn.xiaochuankeji.tieba.network.custom;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.network.d;
import java.io.IOException;
import java.net.URI;
import okhttp3.u;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements d {
    public y a(y yVar) {
        y$a f = yVar.f();
        URI a = yVar.a().a();
        a.getHost();
        a.getPath();
        if (yVar.b().equalsIgnoreCase("post")) {
            z d = yVar.d();
            u contentType = d.contentType();
            if (contentType == null || contentType.toString().contains("text/plain") || contentType.toString().contains("application/json")) {
                Object buffer = new Buffer();
                try {
                    d.writeTo(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer = buffer.readUtf8();
                try {
                    JSONObject jSONObject;
                    if (TextUtils.isEmpty(buffer)) {
                        jSONObject = new JSONObject();
                    } else {
                        jSONObject = new JSONObject(buffer);
                    }
                    a.a(jSONObject);
                    f.a(z.create(u.a("text/plain; charset=utf-8"), jSONObject.toString()));
                    f.b("Request-Type", "text/json");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        f.b("ZYP", "mid=" + cn.xiaochuankeji.tieba.background.a.g().c());
        return f.d();
    }
}
