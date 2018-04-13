package cn.htjyb.netlib;

import android.os.AsyncTask;
import cn.htjyb.netlib.d.a;
import cn.xiaochuankeji.tieba.network.NetCrypto;
import org.json.JSONObject;

public class f extends d {
    public f(String str, b bVar, JSONObject jSONObject, a aVar) {
        super(NetCrypto.a(str, jSONObject), bVar, jSONObject, aVar);
    }

    protected void a(AsyncTask asyncTask) {
        this.c = this.f.a(this, this.e, this.g.toString());
    }
}
