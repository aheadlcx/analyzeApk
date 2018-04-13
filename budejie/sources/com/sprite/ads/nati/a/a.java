package com.sprite.ads.nati.a;

import android.view.View;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.NativeADListener;
import com.sprite.ads.nati.NativeAdRef;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.nati.reporter.SelfReporter;

public class a extends NativeAdRef {
    private AdItem a;
    private Reporter b;
    private NativeADListener c;
    private int d;
    private int e;

    private void c() {
        if (this.a != null) {
            this.a.position = this.d;
        }
    }

    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(AdItem adItem) {
        this.a = adItem;
    }

    public void a(NativeADListener nativeADListener) {
        this.c = nativeADListener;
    }

    public void a(Reporter reporter) {
        this.b = reporter;
    }

    public AdItem b() {
        return this.a;
    }

    public void b(int i) {
        this.e = i;
    }

    public DataSourceType getDataSourceType() {
        return this.a.getDataSourceType();
    }

    public int getPosition() {
        return this.d;
    }

    public String getResType() {
        return this.a.getResType();
    }

    public void onClicked(View view) {
        c();
        if (this.b == null) {
            return;
        }
        if (this.b instanceof SelfReporter) {
            ((SelfReporter) this.b).onClicked(view, new com.sprite.ads.internal.a.c.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick() {
                    if (this.a.c != null) {
                        this.a.c.onADSkip(this.a.a);
                    }
                }

                public void onDismiss() {
                }

                public void onPositive() {
                }
            });
        } else {
            this.b.onClicked(view);
        }
    }

    public void onExposured(View view) {
        c();
        if (this.b != null) {
            this.b.onExposured(view);
        }
    }

    public void onPlay(View view) {
        c();
        if (this.b != null) {
            this.b.onPlay(view);
        }
    }

    public String toString() {
        return "position:" + this.d + "  dataSourceType:" + getDataSourceType() + "," + "  adItem:" + this.a + "," + "  reporter:" + this.b;
    }
}
