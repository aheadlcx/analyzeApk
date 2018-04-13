package com.sprite.ads.nati.reporter;

import android.app.Activity;
import android.view.View;
import com.sprite.ads.internal.a.c;
import com.sprite.ads.internal.a.c.a;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;

public class SelfReporter extends ConfirmDownloadReporter {
    SelfItem adItem;

    public SelfReporter(AdItem adItem) {
        this.adItem = (SelfItem) adItem;
    }

    public void onClicked(View view) {
        onClicked(view, null);
    }

    public void onClicked(View view, a aVar) {
        c.a().a((Activity) view.getContext());
        c.a().a(aVar, this.adItem, this.adItem.downwarn);
    }

    public void onExposured(View view) {
        c.a().a((Activity) view.getContext());
        c.a().a(this.adItem);
    }

    public void onPlay(View view) {
        c.a().a((Activity) view.getContext());
        c.a().b(this.adItem);
    }
}
