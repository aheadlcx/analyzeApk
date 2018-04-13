package com.budejie.www.ad;

import android.content.Context;
import com.budejie.www.busevent.a;
import com.budejie.www.util.ai;
import de.greenrobot.event.EventBus;

public class AdManager {
    private static boolean isClosed;

    public static void closeAd() {
        isClosed = true;
    }

    public static void openAd() {
        isClosed = false;
    }

    public static boolean isAdClosed() {
        return isClosed;
    }

    public static void check(Context context) {
        if (isVIP(context)) {
            closeAd();
        } else {
            openAd();
        }
    }

    public static boolean isVIP(Context context) {
        return ai.c(context);
    }

    public static void closeAdAndUpdateList() {
        closeAd();
        EventBus.getDefault().post(new a());
    }
}
