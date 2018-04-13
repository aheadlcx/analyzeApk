package cn.xiaochuankeji.tieba.network.custom;

import cn.xiaochuankeji.tieba.network.a;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import com.izuiyou.a.a.b;
import okhttp3.aa;

public class c implements a {
    private final int a = -11;

    public void a(Throwable th) {
        if ((th instanceof ClientErrorException) && ((ClientErrorException) th).errCode() == -11) {
            b.d("401:request error: token auth fail");
            cn.xiaochuankeji.tieba.background.a.g().f();
        }
    }

    public void a(aa aaVar) {
        if (aaVar.b() == 401) {
            b.d("401:request error: token auth fail");
            cn.xiaochuankeji.tieba.background.a.g().f();
        }
    }
}
