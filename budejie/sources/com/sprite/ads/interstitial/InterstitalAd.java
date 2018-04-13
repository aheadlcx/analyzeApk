package com.sprite.ads.interstitial;

import android.app.Activity;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import java.lang.reflect.InvocationTargetException;

public class InterstitalAd {
    static InterstitalAdapter adapter;

    private static InterstitalAdapter create(String str, AdItem adItem, ADConfig aDConfig) {
        return (InterstitalAdapter) Class.forName(str).getConstructor(new Class[]{AdItem.class, ADConfig.class}).newInstance(new Object[]{adItem, aDConfig});
    }

    public static void create(AdItem adItem, ADConfig aDConfig) {
        try {
            adapter = create("com.sprite.ads.third.gdt.interstitial.InterstitalAd", adItem, aDConfig);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        } catch (InstantiationException e5) {
            e5.printStackTrace();
        }
    }

    public static void loadInterstitialAd(Activity activity) {
        if (adapter == null) {
            create(null, null);
        }
        if (adapter != null) {
            adapter.loadInterstitialAd(activity);
        }
    }

    public static void loadInterstitialAd(Activity activity, String str) {
        if (adapter == null) {
            create(null, null);
        }
        if (adapter != null) {
            adapter.loadInterstitialAd(activity, str);
        }
    }
}
