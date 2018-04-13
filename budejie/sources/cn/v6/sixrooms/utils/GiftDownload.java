package cn.v6.sixrooms.utils;

import android.content.Context;
import android.os.Environment;
import cn.v6.sixrooms.net.NetworkState;
import java.io.File;

public class GiftDownload {
    private static GiftDownload d;
    Runnable a = new i(this);
    private String b;
    private String c;
    private CallBack e;
    private Context f;
    private long g;

    public interface CallBack {
        void error();

        void finsh(String str, int i);
    }

    public GiftDownload(Context context) {
        this.f = context;
        if (Environment.getExternalStorageState().equals("mounted")) {
            this.b = Environment.getExternalStorageDirectory().getAbsolutePath() + "/6rooms/gift/";
            this.c = this.b + "smily.data";
            return;
        }
        this.c = context.getCacheDir() + "/smily.data";
    }

    public void setCallback(CallBack callBack) {
        this.e = callBack;
    }

    public static GiftDownload getInstance(Context context) {
        if (d == null) {
            d = new GiftDownload(context);
        }
        return d;
    }

    public void getGift() {
        if (GlobleValue.totalSize > 0 && new File(this.c).exists()) {
            this.e.finsh(this.c, GlobleValue.totalSize);
        } else if (NetworkState.checkNet(this.f)) {
            new Thread(this.a).start();
        }
    }
}
