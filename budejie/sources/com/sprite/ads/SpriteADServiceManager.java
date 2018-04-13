package com.sprite.ads;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import com.facebook.common.util.UriUtil;
import com.sprite.ads.SpriteADService.Command;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.log.ADLog;

public class SpriteADServiceManager {
    private static SpriteADServiceManager instance = new SpriteADServiceManager();
    private static Context mContext;

    public static SpriteADServiceManager getInstance() {
        return instance;
    }

    private String parseUrl(String str) {
        return str.startsWith("down") ? str.replaceFirst("down", UriUtil.HTTP_SCHEME) : str;
    }

    public Context getContext() {
        return mContext;
    }

    public void installApk(String str, String str2) {
        if (!str.endsWith(".apk")) {
            str = str.concat(".apk");
        }
        Intent intent = new Intent(mContext, SpriteADService.class);
        intent.putExtra("command", Command.INSTALL_APK);
        intent.putExtra("apk_name", str);
        intent.putExtra("apk_path", str2);
        mContext.startService(intent);
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void startDownload(AdItem adItem) {
        if (mContext == null) {
            ADLog.d("下载服务无法开启，由于mContext为null");
        } else if (Environment.getExternalStorageDirectory() == null) {
            ADLog.d("下载服务无法开启，sd卡路径创建失败");
        } else {
            String concat = Environment.getExternalStorageDirectory().getPath().concat("/").concat(mContext.getPackageName().replace(".", "")).concat("/down");
            Intent intent = new Intent(mContext, SpriteADService.class);
            intent.putExtra("command", Command.DOWNLOAD);
            intent.putExtra("download_path", concat);
            intent.putExtra("url", parseUrl(adItem.getUrl()));
            intent.putExtra("aditem", adItem);
            mContext.startService(intent);
        }
    }
}
