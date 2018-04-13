package com.sprite.ads.third.linkactive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.sprite.ads.R$integer;
import com.sprite.ads.internal.a.b;
import com.sprite.ads.internal.a.c;
import com.sprite.ads.internal.a.c.a;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.LinkActiveAdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.nati.reporter.ConfirmDownloadReporter;
import com.sprite.ads.nati.reporter.Reporter.OnChangeAdListener;
import java.net.URISyntaxException;

public class LinkActiveReporter extends ConfirmDownloadReporter {
    private LinkActiveAdItem mAdItem;
    private Context mContext;
    private OnChangeAdListener mOnChangeAdListener;

    public LinkActiveReporter(LinkActiveAdData linkActiveAdData) {
        this.mAdItem = linkActiveAdData.getAdItem();
    }

    private void changeAd() {
        if (this.mOnChangeAdListener != null) {
            this.mOnChangeAdListener.onChange();
        }
    }

    private void openAppWithPN(String str, String str2, String str3, String str4, String str5) {
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setFlags(268435456);
            launchIntentForPackage.setData(Uri.parse(str2));
            this.mContext.startActivity(launchIntentForPackage);
            recordStatus(13);
            changeAd();
            return;
        }
        recordStatus(14);
        if (TextUtils.isEmpty(str4)) {
            recordStatus(15);
            openH5Url(str3);
            changeAd();
            return;
        }
        AdItem selfItem = new SelfItem();
        selfItem.setIsDownListener(false);
        selfItem.setUrl(str4);
        selfItem.setTitle(str5);
        c.a().a((Activity) this.mContext);
        c.a().b(new a() {
            public void onClick() {
            }

            public void onDismiss() {
            }

            public void onPositive() {
                LinkActiveReporter.this.recordStatus(15);
                LinkActiveReporter.this.changeAd();
            }
        }, selfItem, this.downwarn);
    }

    private void openH5Url(String str) {
        b bVar = new b();
        AdItem selfItem = new SelfItem();
        selfItem.setUrl(str);
        bVar.b(this.mContext, selfItem);
    }

    private void recordStatus(int i) {
        if (this.mAdItem != null && !TextUtils.isEmpty(this.mAdItem.record_status_url)) {
            com.sprite.ads.internal.net.a.a(this.mAdItem.record_status_url + "&status=" + i, new com.sprite.ads.internal.net.c());
        }
    }

    public void onClicked(View view) {
        if (this.mAdItem != null) {
            this.mContext = view.getContext();
            Object tag = view.getTag(R$integer.native_ad_view_tag_key);
            if (tag != null && (tag instanceof OnChangeAdListener)) {
                this.mOnChangeAdListener = (OnChangeAdListener) tag;
            }
            recordStatus(12);
            String str = this.mAdItem.uri_scheme;
            String str2 = this.mAdItem.pkg_name;
            String str3 = this.mAdItem.h5_url;
            String str4 = this.mAdItem.apk_url;
            String str5 = this.mAdItem.title;
            try {
                Intent parseUri = Intent.parseUri(str, 1);
                parseUri.setPackage(str2);
                parseUri.setFlags(268435456);
                if (this.mContext.getPackageManager().resolveActivity(parseUri, 65536) != null) {
                    this.mContext.startActivity(parseUri);
                    recordStatus(13);
                    changeAd();
                    return;
                }
                openAppWithPN(str2, str, str3, str4, str5);
            } catch (URISyntaxException e) {
                openAppWithPN(str2, str, str3, str4, str5);
            }
        }
    }

    public void onExposured(View view) {
        recordStatus(11);
    }

    public void onPlay(View view) {
    }
}
