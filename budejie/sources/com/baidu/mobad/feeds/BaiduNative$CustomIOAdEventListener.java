package com.baidu.mobad.feeds;

import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.openad.c.b;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class BaiduNative$CustomIOAdEventListener implements IOAdEventListener {
    final /* synthetic */ BaiduNative a;
    private IXAdFeedsRequestParameters b;

    public BaiduNative$CustomIOAdEventListener(BaiduNative baiduNative, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.a = baiduNative;
        this.b = iXAdFeedsRequestParameters;
    }

    public void run(IOAdEvent iOAdEvent) {
        if (IXAdEvent.AD_STARTED.equals(iOAdEvent.getType())) {
            BaiduNative.a(this.a).removeAllListeners();
            if (BaiduNative.b(this.a) != null) {
                final List arrayList = new ArrayList();
                HashSet hashSet = new HashSet();
                IXAdConstants adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
                for (int i = 0; i < BaiduNative.a(this.a).q().size(); i++) {
                    boolean z;
                    boolean z2;
                    XAdNativeResponse xAdNativeResponse;
                    IXAdInstanceInfo iXAdInstanceInfo = (IXAdInstanceInfo) BaiduNative.a(this.a).q().get(i);
                    String appPackageName = iXAdInstanceInfo.getAppPackageName();
                    if (iXAdInstanceInfo.getActionType() == adConstants.getActTypeDownload()) {
                        if (appPackageName == null || appPackageName.equals("") || appPackageName.equals("null") || hashSet.contains(appPackageName)) {
                            z = false;
                            z2 = true;
                            if (z2) {
                                xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, this.a, this.b, BaiduNative.a(this.a).getCurrentXAdContainer());
                                if (z) {
                                    xAdNativeResponse.setIsDownloadApp(false);
                                }
                                arrayList.add(xAdNativeResponse);
                            }
                        } else {
                            hashSet.add(appPackageName);
                            if (XAdSDKFoundationFacade.getInstance().getPackageUtils().isInstalled(BaiduNative.c(this.a), appPackageName)) {
                                z = true;
                                z2 = false;
                                if (z2) {
                                    xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, this.a, this.b, BaiduNative.a(this.a).getCurrentXAdContainer());
                                    if (z) {
                                        xAdNativeResponse.setIsDownloadApp(false);
                                    }
                                    arrayList.add(xAdNativeResponse);
                                }
                            }
                        }
                    }
                    z = false;
                    z2 = false;
                    if (z2) {
                        xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, this.a, this.b, BaiduNative.a(this.a).getCurrentXAdContainer());
                        if (z) {
                            xAdNativeResponse.setIsDownloadApp(false);
                        }
                        arrayList.add(xAdNativeResponse);
                    }
                }
                XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new Runnable(this) {
                    final /* synthetic */ BaiduNative$CustomIOAdEventListener b;

                    public void run() {
                        BaiduNative.b(this.b.a).onNativeLoad(arrayList);
                    }
                });
            }
        }
        if (IXAdEvent.AD_ERROR.equals(iOAdEvent.getType())) {
            BaiduNative.a(this.a).removeAllListeners();
            String str = (String) iOAdEvent.getData().get(b.EVENT_MESSAGE);
            if (BaiduNative.b(this.a) != null) {
                XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new Runnable(this) {
                    final /* synthetic */ BaiduNative$CustomIOAdEventListener a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        BaiduNative.b(this.a.a).onNativeFail(NativeErrorCode.LOAD_AD_FAILED);
                    }
                });
            }
        }
    }
}
