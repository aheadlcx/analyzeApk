package com.baidu.mobads.b;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.budejie.www.R$styleable;

public class a extends BroadcastReceiver {
    protected final IXAdLogger a = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private com.baidu.mobads.command.a b;

    public a(com.baidu.mobads.command.a aVar) {
        this.b = aVar;
    }

    @TargetApi(3)
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String replace = intent.getDataString().replace("package:", "");
            if (replace.equals(this.b.i)) {
                IXAdPackageUtils packageUtils = XAdSDKFoundationFacade.getInstance().getPackageUtils();
                if (this.b.w && this.b.x != null && !this.b.x.equals("")) {
                    IXAdConstants adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
                    if (packageUtils.sendAPOInfo(context, this.b.x, replace, R$styleable.Theme_Custom_new_detail_title_bg, adConstants.getActTypeDownload(), 0)) {
                        XAdSDKFoundationFacade.getInstance().getCommonUtils().browserOutside(context, this.b.x);
                    }
                    context.unregisterReceiver(this);
                } else if (this.b.l) {
                    try {
                        Thread.sleep(600);
                        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(replace);
                        launchIntentForPackage.addFlags(268435456);
                        context.startActivity(launchIntentForPackage);
                        context.unregisterReceiver(this);
                    } catch (Throwable e) {
                        this.a.d("InstallReceiver", e);
                    }
                }
            }
        }
    }
}
