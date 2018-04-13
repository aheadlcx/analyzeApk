package com.baidu.mobads;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

public class AdService {
    protected static String a = "";
    protected static int b = -1;
    private AdView c;

    public static void setChannelId(String str) {
        a = str;
        XAdSDKFoundationFacade.getInstance().getCommonUtils().setChannelId(str);
    }

    public AdService(Context context, ViewGroup viewGroup, LayoutParams layoutParams, AdViewListener adViewListener) {
        this(context, viewGroup, layoutParams, adViewListener, AdSize.Banner, "");
    }

    public AdService(Context context, ViewGroup viewGroup, LayoutParams layoutParams, AdViewListener adViewListener, AdSize adSize, String str) {
        if (context == null || viewGroup == null || layoutParams == null || adViewListener == null || adSize == null) {
            throw new IllegalArgumentException("One of arguments is null");
        }
        this.c = new AdView(context, false, adSize, str);
        this.c.setListener(adViewListener);
        a(viewGroup, layoutParams);
        b++;
    }

    private void a(ViewGroup viewGroup, LayoutParams layoutParams) {
        try {
            if (this.c.getParent() != viewGroup) {
                if (this.c.getParent() != null) {
                    ((ViewGroup) this.c.getParent()).removeView(this.c);
                }
                viewGroup.addView(this.c, layoutParams);
            }
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
        }
    }

    public void destroy() {
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
        }
    }
}
