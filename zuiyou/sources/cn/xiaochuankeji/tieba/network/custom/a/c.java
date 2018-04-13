package cn.xiaochuankeji.tieba.network.custom.a;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.network.NetCrypto;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.u;
import okhttp3.y;
import okhttp3.z;
import okio.Buffer;

public class c implements t {
    public aa intercept(a aVar) throws IOException {
        y a = aVar.a();
        if (a.b().equalsIgnoreCase("post")) {
            z d = a.d();
            u contentType = d.contentType();
            if (!(d == null || contentType == null || (!contentType.toString().contains("text/plain") && !contentType.toString().contains("application/json")))) {
                Object buffer = new Buffer();
                d.writeTo(buffer);
                String readUtf8 = buffer.readUtf8();
                if (!TextUtils.isEmpty(readUtf8)) {
                    try {
                        a = a.f().a(NetCrypto.a(a.a().toString(), readUtf8)).d();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return aVar.a(a);
    }
}
