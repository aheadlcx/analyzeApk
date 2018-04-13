package cn.xiaochuan.jsbridge;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import com.sensetime.stmobile.STMobileHumanActionNative;

public class a {
    public static void a(f fVar, Uri uri, String str) {
        Context context = fVar.getContext();
        WebSettings settings = fVar.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setDatabasePath(context.getDatabasePath("zuiyou_web").getAbsolutePath());
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(STMobileHumanActionNative.ST_MOBILE_DETECT_EYEBALL_CENTER);
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setCacheMode(-1);
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        Object userAgentString = settings.getUserAgentString();
        if (TextUtils.isEmpty(userAgentString)) {
            settings.setUserAgentString("Mozilla/5.0 (Linux; Android; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36  Zuiyou/" + str);
        } else if (!userAgentString.contains(" Zuiyou/")) {
            settings.setUserAgentString(settings.getUserAgentString() + " Zuiyou/" + str);
        }
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        fVar.setBackgroundColor(Color.argb(1, 0, 0, 0));
        if (VERSION.SDK_INT > 15) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 19) {
            try {
                settings.setLayoutAlgorithm(LayoutAlgorithm.TEXT_AUTOSIZING);
            } catch (Throwable e) {
                e.printStackTrace();
                com.izuiyou.a.a.a.a(e);
            }
        }
        settings.setSupportZoom(true);
        fVar.requestFocus(130);
    }
}
