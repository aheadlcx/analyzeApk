package com.sprite.ads.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.sprite.ads.internal.a.c;
import com.sprite.ads.internal.a.c.a;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.ADExtra;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;

public class AdDialog extends AlertDialog {
    ADConfig mAdConfig;
    SelfItem mAdItem;

    protected AdDialog(Context context, AdItem adItem, ADConfig aDConfig) {
        super(context);
        this.mAdItem = (SelfItem) adItem;
        this.mAdConfig = aDConfig;
    }

    public void show(final Context context) {
        Builder positiveButton = new Builder(context).setTitle(this.mAdItem.getTitle()).setMessage(this.mAdItem.getDesc()).setPositiveButton("确定", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                c.a().a((Activity) context);
                c.a().a(new a() {
                    public void onClick() {
                    }

                    public void onDismiss() {
                    }

                    public void onPositive() {
                    }
                }, AdDialog.this.mAdItem, AdDialog.this.mAdItem.downwarn);
            }
        });
        ADExtra aDExtra = this.mAdItem.extra;
        if (!(aDExtra == null || "0".equalsIgnoreCase(aDExtra.sdkBtnCancel) || !"1".equalsIgnoreCase(aDExtra.sdkBtnCancel))) {
            positiveButton.setNegativeButton("取消", new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        positiveButton.show();
    }
}
