package com.budejie.www.adapter.d;

import android.app.Activity;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.adapter.g.b;
import com.budejie.www.adapter.g.c;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.NativeAdRef;
import com.sprite.ads.nati.reporter.Reporter.OnChangeAdListener;
import com.sprite.ads.nati.view.NativeAdDataLoadedListener;
import com.sprite.ads.nati.view.NativeAdView;
import com.tencent.connect.common.Constants;

public class g extends h {
    public g(Activity activity, c cVar, b<ListItemObject> bVar) {
        super(activity, cVar, bVar);
    }

    public View b() {
        View nativeAdView = new NativeAdView(this.a);
        View b = super.b();
        final com.budejie.www.adapter.b bVar = (com.budejie.www.adapter.b) b.getTag();
        bVar.G = nativeAdView;
        nativeAdView.setTag(bVar);
        nativeAdView.addView(b);
        if (b != null) {
            TextView textView = (TextView) b.findViewById(R.id.ad_six_room_change_text);
            if (textView != null) {
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ g b;

                    public void onClick(View view) {
                        i.a("点击广告", "信息流|点击换一批");
                        this.b.a(bVar);
                    }
                });
            }
        }
        return nativeAdView;
    }

    public void a(final com.budejie.www.adapter.b bVar) {
        ((ListItemObject) this.c.b).setVideouri(null);
        NativeAdRef adItem = ((ListItemObject) this.c.b).getAdItem();
        if (adItem == null) {
            a(bVar.G);
            return;
        }
        bVar.G.setOnChangeAdListener(new OnChangeAdListener(this) {
            final /* synthetic */ g b;

            public void onChange() {
                this.b.a(bVar);
            }
        });
        bVar.G.loadAd(adItem, new NativeAdDataLoadedListener(this) {
            final /* synthetic */ g b;

            public void onAdLoaded(NativeAdData nativeAdData) {
                if (nativeAdData == null) {
                    this.b.a(bVar.G);
                    return;
                }
                this.b.b(bVar.G);
                this.b.a((ListItemObject) this.b.c.b, nativeAdData);
                super.a(bVar);
                TextView textView = (TextView) bVar.G.findViewById(R.id.ad_action_type);
                if (textView != null) {
                    textView.setText(nativeAdData.getActionType() == 2 ? "下载" : "打开");
                }
                textView = (TextView) bVar.G.findViewById(R.id.content);
                if (textView != null) {
                    textView.setMaxLines(5);
                    textView.setEllipsize(TruncateAt.END);
                }
            }
        });
    }

    private void a(ListItemObject listItemObject, NativeAdData nativeAdData) {
        listItemObject.setContent(nativeAdData.getDesc());
        listItemObject.setWidth(16);
        listItemObject.setHeight(9);
        listItemObject.setImgUrl(nativeAdData.getPic());
        listItemObject.setAd_url(nativeAdData.getUrl());
        listItemObject.setVideouri(nativeAdData.getMovie());
        listItemObject.setType(a(nativeAdData.getResType()));
        listItemObject.setAdItemData(nativeAdData);
    }

    private String a(String str) {
        if ("movie".equals(str)) {
            return "41";
        }
        return Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
    }

    private void a(NativeAdView nativeAdView) {
        LayoutParams layoutParams = nativeAdView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, 1);
        } else {
            layoutParams.height = 1;
        }
        nativeAdView.setLayoutParams(layoutParams);
    }

    private void b(NativeAdView nativeAdView) {
        LayoutParams layoutParams = nativeAdView.getLayoutParams();
        if (layoutParams != null && layoutParams.height == 1) {
            layoutParams.height = -2;
            nativeAdView.setLayoutParams(layoutParams);
        }
    }
}
