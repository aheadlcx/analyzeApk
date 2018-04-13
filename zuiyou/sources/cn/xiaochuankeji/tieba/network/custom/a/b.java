package cn.xiaochuankeji.tieba.network.custom.a;

import cn.xiaochuankeji.tieba.background.utils.a.d;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.t.a;

public class b extends d {
    public aa intercept(a aVar) throws IOException {
        try {
            aa intercept = super.intercept(aVar);
            if (d.a().b()) {
                d.a().a(intercept.m() - intercept.l(), intercept.a("Content-Type"), intercept.b(), intercept.d(), intercept.a().a().toString(), intercept.g().contentLength() + "");
            }
            return intercept;
        } catch (IOException e) {
            d.a().a(0, aVar.a().a("Content-Type"), 0, e.toString(), aVar.a().a().toString(), "0");
            throw e;
        }
    }
}
