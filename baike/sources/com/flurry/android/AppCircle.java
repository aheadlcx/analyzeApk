package com.flurry.android;

import android.content.Context;
import android.view.View;
import java.util.List;

public class AppCircle {
    public void launchCanvasOnBannerClicked(boolean z) {
        FlurryAgent.a(z);
    }

    public void launchCatalogOnBannerClicked(boolean z) {
        FlurryAgent.a(z);
    }

    public boolean isLaunchCanvasOnBannerClicked() {
        return FlurryAgent.a();
    }

    public boolean isLaunchCatalogOnBannerClicked() {
        return FlurryAgent.a();
    }

    public View getHook(Context context, String str, int i) {
        return FlurryAgent.a(context, str, i);
    }

    public void openCatalog(Context context) {
        openCatalog(context, "");
    }

    public void openCatalog(Context context, String str) {
        FlurryAgent.a(context, str);
    }

    public boolean hasAds() {
        return FlurryAgent.d();
    }

    public Offer getOffer() {
        return getOffer("");
    }

    public Offer getOffer(String str) {
        return FlurryAgent.a(str);
    }

    public List getAllOffers() {
        return FlurryAgent.b("");
    }

    public List getAllOffers(String str) {
        return FlurryAgent.b(str);
    }

    public void acceptOffer(Context context, long j) {
        FlurryAgent.a(context, j);
    }

    public void removeOffers(List list) {
        FlurryAgent.a(list);
    }

    public void setDefaultNoAdsMessage(String str) {
        FlurryAgent.setDefaultNoAdsMessage(str);
    }

    public void setAppCircleCallback(AppCircleCallback appCircleCallback) {
        FlurryAgent.a(appCircleCallback);
    }

    public void addUserCookie(String str, String str2) {
        FlurryAgent.addUserCookie(str, str2);
    }

    public void clearUserCookies() {
        FlurryAgent.clearUserCookies();
    }
}
