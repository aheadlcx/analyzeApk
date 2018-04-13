package com.sprite.ads;

import android.content.Context;
import com.sprite.ads.SpriteInitiator.Param;
import com.sprite.ads.internal.imageCache.AdImageLoader;
import com.sprite.ads.internal.net.a;
import com.sprite.ads.internal.utils.AdUtil;
import com.sprite.ads.internal.utils.ViewUtil;
import java.util.List;

public final class SpriteAD implements Constants {
    private static boolean isInit = false;
    private static Context mApplicationContext;
    private static SpriteInitiator spriteInitiator = new SpriteInitiator();

    public static void applicationInit(Context context) {
        spriteInitiator.applicationInit(context);
    }

    public static void applicationInit(Context context, List<Param> list) {
        spriteInitiator.applicationInit(context, list);
    }

    public static Context getApplicationContext() {
        return mApplicationContext;
    }

    public static int getVersionCode() {
        return 15;
    }

    public static String getVersionName() {
        return Constants.SDK_VERSION;
    }

    public static void init(Context context) {
        mApplicationContext = context.getApplicationContext();
        a.a(context);
        AdImageLoader.getInstance().init(context);
        SpriteADServiceManager.getInstance().setContext(context);
        setScreen(ViewUtil.SCREEN_WIDTH + "x" + ViewUtil.SCREEN_HEIGHT);
        setApp(context.getPackageName());
        setAppVersion(AdUtil.getAppVersion(context));
        a.b(context);
        isInit = true;
    }

    public static boolean isInit() {
        return isInit;
    }

    public static void setApp(String str) {
        a.f(str);
    }

    public static void setAppKey(String str) {
        a.b(str);
    }

    public static void setAppVersion(String str) {
        a.a(str);
    }

    public static void setMarket(String str) {
        a.d(str);
    }

    public static void setScreen(String str) {
        a.c(str);
    }

    public static void setUid(String str) {
        a.e(str);
    }
}
