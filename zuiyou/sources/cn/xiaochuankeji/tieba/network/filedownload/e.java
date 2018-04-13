package cn.xiaochuankeji.tieba.network.filedownload;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import com.liulishuo.filedownloader.e.b;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.i;
import com.liulishuo.filedownloader.q;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import org.apache.commons.io.c;

public class e {
    private static final b<d> a = new b();

    public static void a(Application application) {
        d.a = false;
        q.a(application).a(new c()).a();
    }

    static b<d> a() {
        return a;
    }

    public static void a(@NonNull String str) {
        File a;
        String c = c.c(Uri.parse(str).getPath());
        if (TextUtils.isEmpty(c)) {
            c = String.valueOf(System.currentTimeMillis());
        }
        File file = new File(a.e().D(), c);
        if (file.exists()) {
            a = a(c, 1);
        } else {
            a = file;
        }
        q.a().a(str).a(a.getAbsolutePath()).a(str).a(new f()).c();
    }

    public static void b(@NonNull String str) {
        File file = new File(a.e().s(), "zuiyou.apk");
        q a = q.a();
        a.a(1);
        com.liulishuo.filedownloader.a a2 = a.a(str);
        if (!com.liulishuo.filedownloader.d.d.b(a.a(a2.d(), str))) {
            a2.a(file.getAbsolutePath()).a(str).a(new a()).a(20).b(3).b(false).a(true).c();
        }
    }

    public static void a(@NonNull String str, @NonNull i iVar) {
        File file = new File(a.e().s(), cn.htjyb.c.d.c(str) + ShareConstants.PATCH_SUFFIX);
        q a = q.a();
        a.a(1);
        com.liulishuo.filedownloader.a a2 = a.a(str);
        if (com.liulishuo.filedownloader.d.d.b(a.a(a2.d(), str))) {
            g.a("正在下载，请稍后");
        } else {
            a2.a(file.getAbsolutePath()).a(str).a(20).b(3).b(false).a(false).a(iVar).c();
        }
    }

    private static File a(String str, int i) {
        String c;
        StringBuilder append = new StringBuilder().append(c.d(str)).append("-(");
        if (i > 100) {
            c = cn.htjyb.c.d.c(str);
        } else {
            c = String.valueOf(i);
        }
        File file = new File(a.e().D(), append.append(c).append(").").append(c.e(str)).toString());
        if (file.exists()) {
            return a(str, i + 1);
        }
        return file;
    }

    public static void a(int i, int i2, String str, String str2, String str3) {
        if (com.facebook.common.util.d.a(Uri.parse(str3))) {
            Context a = com.liulishuo.filedownloader.g.c.a();
            Builder builder = new Builder(a, "下载");
            builder.setDefaults(4).setPriority(1).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.mipush_small_notification);
            Intent intent = new Intent(a, DownloadReceiver.class);
            intent.setAction("cn.xiaochuan.download.retry");
            intent.putExtra("download_url", str3);
            intent.putExtra("download_type", i);
            builder.setContentIntent(PendingIntent.getBroadcast(a, i2, intent, 134217728));
            cn.xiaochuankeji.tieba.push.e.a().a(i2, builder.build());
        }
    }
}
