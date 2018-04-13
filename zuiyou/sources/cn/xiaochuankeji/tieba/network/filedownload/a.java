package cn.xiaochuankeji.tieba.network.filedownload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.content.FileProvider;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.utils.g;
import com.izuiyou.a.a.b;
import com.liulishuo.filedownloader.e.c;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.zhihu.matisse.BuildConfig;
import java.io.File;

public class a extends c {
    public a() {
        super(e.a());
    }

    protected com.liulishuo.filedownloader.e.a a(com.liulishuo.filedownloader.a aVar) {
        return new d(aVar.d(), "最右", "");
    }

    public void b(com.liulishuo.filedownloader.a aVar) {
        super.b(aVar);
    }

    public void c(com.liulishuo.filedownloader.a aVar) {
        super.c(aVar);
    }

    protected boolean a(com.liulishuo.filedownloader.a aVar, com.liulishuo.filedownloader.e.a aVar2) {
        return false;
    }

    protected boolean d(com.liulishuo.filedownloader.a aVar) {
        return false;
    }

    protected void a(com.liulishuo.filedownloader.a aVar, Throwable th) {
        super.a(aVar, th);
        b.b(th);
        e.a(2, aVar.d(), "应用下载失败", "点击重试", aVar.e());
    }

    protected void a(com.liulishuo.filedownloader.a aVar, int i, int i2) {
        super.a(aVar, i, i2);
        e.a(2, aVar.d(), "应用下载pending", "点击重试", aVar.e());
    }

    protected void b(com.liulishuo.filedownloader.a aVar, int i, int i2) {
        super.b(aVar, i, i2);
    }

    protected void a(com.liulishuo.filedownloader.a aVar, Throwable th, int i, int i2) {
        super.a(aVar, th, i, i2);
    }

    protected void e(com.liulishuo.filedownloader.a aVar) {
        g.b("下载完成");
        try {
            a(BaseApplication.getAppContext(), new File(aVar.h()));
        } catch (Throwable e) {
            e.printStackTrace();
            cn.xiaochuankeji.tieba.analyse.a.a(e);
        }
        super.e(aVar);
    }

    void a(Context context, File file) {
        if (VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(context, BuildConfig.PROVIDER, file);
            Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
            intent.setData(uriForFile);
            intent.setFlags(1);
            context.startActivity(intent);
            return;
        }
        uriForFile = Uri.fromFile(file);
        intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }
}
