package com.baidu.mobads.utils;

import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import com.baidu.mobads.interfaces.utils.IXAdViewUtils;

public class t implements IXAdViewUtils {
    public boolean isAdViewOutsideScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Rect windowRect = XAdSDKFoundationFacade.getInstance().getCommonUtils().getWindowRect(view.getContext());
        int width = iArr[0] + (view.getWidth() / 2);
        int height = iArr[1] + (view.getHeight() / 2);
        if (width <= 0 || width >= windowRect.width() || height <= 0 || height >= windowRect.height()) {
            return true;
        }
        return false;
    }

    public boolean isScreenOn(Context context) {
        try {
            return ((PowerManager) context.getSystemService("power")).isScreenOn();
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
            return true;
        }
    }

    public boolean isAdViewTooSmall(View view) {
        return view != null && (view.getWidth() < 10 || view.getHeight() < 10);
    }

    public boolean isAdViewShown(View view) {
        if (view == null || !view.isShown()) {
            return false;
        }
        return true;
    }

    public boolean isVisible(View view, int i) {
        if (view == null || view.getVisibility() != 0 || view.getParent() == null) {
            return false;
        }
        Rect rect = new Rect();
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        long height = ((long) rect.height()) * ((long) rect.width());
        long height2 = ((long) view.getHeight()) * ((long) view.getWidth());
        if (height2 <= 0 || height * 100 < height2 * ((long) i)) {
            return false;
        }
        return true;
    }

    public int getVisiblePercent(View view, Context context) {
        if (view == null) {
            return 0;
        }
        try {
            if (!view.isShown()) {
                return 0;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int i = displayMetrics.heightPixels;
            int i2 = displayMetrics.widthPixels;
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            if (rect.top > i || rect.left > i2) {
                return 0;
            }
            double width = (double) (rect.width() * rect.height());
            return (int) ((width * 100.0d) / ((double) (view.getHeight() * view.getWidth())));
        } catch (Exception e) {
            return 0;
        }
    }

    public int getViewState(View view) {
        if (!isScreenOn(view.getContext())) {
            return 4;
        }
        if (!isAdViewShown(view)) {
            return 1;
        }
        if (!a(view)) {
            return 6;
        }
        if (isVisible(view, 50)) {
            return 0;
        }
        return 3;
    }

    private boolean a(View view) {
        return view.getWidth() > 15 && view.getHeight() > 15;
    }
}
