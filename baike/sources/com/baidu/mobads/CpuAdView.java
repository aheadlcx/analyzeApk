package com.baidu.mobads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobads.CpuInfoManager.UrlListener;
import com.baidu.mobads.component.XAdView;
import com.baidu.mobads.production.b.b;

public final class CpuAdView extends RelativeLayout {
    private b a;

    public CpuAdView(Context context) {
        super(context);
    }

    public CpuAdView(Context context, String str, String str2, UrlListener urlListener) {
        super(context);
        View xAdView = new XAdView(context);
        this.a = new b(context, xAdView, str, str2);
        this.a.request();
        addView(xAdView, new LayoutParams(-1, -1));
    }
}
