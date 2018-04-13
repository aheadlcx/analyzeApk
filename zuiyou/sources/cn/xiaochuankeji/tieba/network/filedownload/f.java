package cn.xiaochuankeji.tieba.network.filedownload;

import android.content.Intent;
import android.net.Uri;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.utils.g;
import com.izuiyou.a.a.b;
import com.liulishuo.filedownloader.e.a;
import com.liulishuo.filedownloader.e.c;
import java.io.File;

public class f extends c {
    f() {
        super(e.a());
    }

    protected a a(com.liulishuo.filedownloader.a aVar) {
        return new d(aVar.d(), "来自最右的图片", "");
    }

    public void b(com.liulishuo.filedownloader.a aVar) {
        super.b(aVar);
    }

    public void c(com.liulishuo.filedownloader.a aVar) {
        super.c(aVar);
    }

    protected boolean a(com.liulishuo.filedownloader.a aVar, a aVar2) {
        return false;
    }

    protected boolean d(com.liulishuo.filedownloader.a aVar) {
        return super.d(aVar);
    }

    protected void a(com.liulishuo.filedownloader.a aVar, int i, int i2) {
        super.a(aVar, i, i2);
    }

    protected void b(com.liulishuo.filedownloader.a aVar, int i, int i2) {
        super.b(aVar, i, i2);
    }

    protected void a(com.liulishuo.filedownloader.a aVar, Throwable th) {
        super.a(aVar, th);
        b.e(th);
        e.a(1, aVar.d(), "图片下载失败", "点击重试", aVar.e());
    }

    protected void e(com.liulishuo.filedownloader.a aVar) {
        g.b("下载完成");
        try {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(new File(aVar.h())));
            BaseApplication.getAppContext().sendBroadcast(intent);
        } catch (Throwable e) {
            e.printStackTrace();
            cn.xiaochuankeji.tieba.analyse.a.a(e);
        }
        super.e(aVar);
    }
}
