package cn.xiaochuankeji.tieba.network.filedownload;

import android.support.v4.app.NotificationCompat.Builder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.push.e;
import com.liulishuo.filedownloader.e.a;
import com.liulishuo.filedownloader.g.c;

public class d extends a {
    private Builder a = new Builder(c.a(), "下载");

    d(int i, String str, String str2) {
        super(i, str, str2);
        this.a.setDefaults(4).setOngoing(true).setPriority(1).setContentTitle(f()).setContentText(str2).setSmallIcon(R.drawable.mipush_small_notification);
    }

    public void a(boolean z, int i, boolean z2) {
        boolean z3;
        String g = g();
        switch (i) {
            case -4:
                g = g + " warn";
                break;
            case -3:
                g = g + " 下载完成";
                break;
            case -2:
                g = g + " 已暂停";
                break;
            case -1:
                g = g + " 下载错误";
                break;
            case 1:
                g = g + " 准备中";
                break;
            case 3:
                g = g + " 下载中";
                break;
            case 5:
                g = g + " 重试";
                break;
            case 6:
                g = g + " 开始下载";
                break;
        }
        float d = (((float) d()) * 100.0f) / ((float) e());
        Builder contentTitle = this.a.setContentTitle(f());
        StringBuilder append = new StringBuilder().append(g);
        if (d > 0.0f) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format(" %.2f", new Object[]{Float.valueOf(d)});
            g = String.format("%s%%", objArr);
        } else {
            g = " ";
        }
        contentTitle.setContentText(append.append(g).toString());
        if (z) {
            this.a.setTicker(f());
        }
        Builder builder = this.a;
        int e = e();
        int d2 = d();
        if (z2) {
            z3 = false;
        } else {
            z3 = true;
        }
        builder.setProgress(e, d2, z3);
        e.a().a(c(), this.a.build());
    }
}
