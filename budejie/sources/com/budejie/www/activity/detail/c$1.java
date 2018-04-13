package com.budejie.www.activity.detail;

import com.budejie.www.R;
import com.budejie.www.util.w;
import com.sprite.ads.banner.BannerADListener;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.reporter.Reporter;

class c$1 extends BannerADListener {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void onADReceive(Reporter reporter, boolean z) {
        c.a(this.a).a(reporter);
        if (!(reporter == null || c.a(this.a).m)) {
            c.a(this.a).m = true;
            reporter.onExposured(c.b(this.a));
        }
        c.b(this.a).setVisibility(0);
        if (z) {
            c.b(this.a).addView(c.c(this.a).getLayoutInflater().inflate(R.layout.item_divide_view, null));
        }
    }

    public void onNoAD(int i) {
        c.a(this.a).b(false);
        c.b(this.a).setVisibility(8);
    }

    public void onADSkip(AdItem adItem) {
        w.a(c.c(this.a), false).a(adItem.getUrl());
    }
}
