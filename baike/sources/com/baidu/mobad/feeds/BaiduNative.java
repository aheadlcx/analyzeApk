package com.baidu.mobad.feeds;

import android.content.Context;
import android.view.View;
import com.baidu.mobad.feeds.RequestParameters.Builder;
import com.baidu.mobads.g.q;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.c.c;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BaiduNative {
    private final Context a;
    private final String b;
    private c c;
    private BaiduNativeNetworkListener d;
    private BaiduNativeEventListener e;

    public interface BaiduNativeEventListener {
        void onClicked();

        void onImpressionSended();
    }

    public interface BaiduNativeNetworkListener {
        void onNativeFail(NativeErrorCode nativeErrorCode);

        void onNativeLoad(List<NativeResponse> list);
    }

    class a implements IOAdEventListener {
        final /* synthetic */ BaiduNative a;
        private IXAdFeedsRequestParameters b;

        public a(BaiduNative baiduNative, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
            this.a = baiduNative;
            this.b = iXAdFeedsRequestParameters;
        }

        public void run(IOAdEvent iOAdEvent) {
            if (IXAdEvent.AD_STARTED.equals(iOAdEvent.getType())) {
                this.a.c.removeAllListeners();
                if (this.a.d != null) {
                    List arrayList = new ArrayList();
                    HashSet hashSet = new HashSet();
                    IXAdConstants adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
                    for (int i = 0; i < this.a.c.o().size(); i++) {
                        boolean z;
                        boolean z2;
                        XAdNativeResponse xAdNativeResponse;
                        IXAdInstanceInfo iXAdInstanceInfo = (IXAdInstanceInfo) this.a.c.o().get(i);
                        String appPackageName = iXAdInstanceInfo.getAppPackageName();
                        if (iXAdInstanceInfo.getActionType() == adConstants.getActTypeDownload()) {
                            if (appPackageName == null || appPackageName.equals("") || appPackageName.equals("null") || hashSet.contains(appPackageName)) {
                                z = false;
                                z2 = true;
                                if (z2) {
                                    xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, this.a, this.b, this.a.c.getCurrentXAdContainer());
                                    if (z) {
                                        xAdNativeResponse.setIsDownloadApp(false);
                                    }
                                    arrayList.add(xAdNativeResponse);
                                }
                            } else {
                                hashSet.add(appPackageName);
                                if (XAdSDKFoundationFacade.getInstance().getPackageUtils().isInstalled(this.a.a, appPackageName)) {
                                    z = true;
                                    z2 = false;
                                    if (z2) {
                                        xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, this.a, this.b, this.a.c.getCurrentXAdContainer());
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
                            xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, this.a, this.b, this.a.c.getCurrentXAdContainer());
                            if (z) {
                                xAdNativeResponse.setIsDownloadApp(false);
                            }
                            arrayList.add(xAdNativeResponse);
                        }
                    }
                    XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new a(this, arrayList));
                }
            }
            if (IXAdEvent.AD_ERROR.equals(iOAdEvent.getType())) {
                this.a.c.removeAllListeners();
                String str = (String) iOAdEvent.getData().get("message");
                if (this.a.d != null) {
                    XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new b(this));
                }
            }
        }
    }

    public BaiduNative(Context context, String str, BaiduNativeNetworkListener baiduNativeNetworkListener) {
        this(context, str, baiduNativeNetworkListener, new c(context, str));
    }

    public BaiduNative(Context context, String str, BaiduNativeNetworkListener baiduNativeNetworkListener, c cVar) {
        this.a = context;
        XAdSDKFoundationFacade.getInstance().initializeApplicationContext(context.getApplicationContext());
        this.b = str;
        this.d = baiduNativeNetworkListener;
        q.a(context).a();
        this.c = cVar;
    }

    public void destroy() {
    }

    @Deprecated
    public void setNativeEventListener(BaiduNativeEventListener baiduNativeEventListener) {
        this.e = baiduNativeEventListener;
    }

    public void makeRequest() {
        makeRequest((RequestParameters) null);
    }

    public void makeRequest(RequestParameters requestParameters) {
        if (requestParameters == null) {
            requestParameters = new Builder().build();
        }
        requestParameters.a = this.b;
        IOAdEventListener aVar = new a(this, requestParameters);
        this.c.addEventListener(IXAdEvent.AD_STARTED, aVar);
        this.c.addEventListener(IXAdEvent.AD_ERROR, aVar);
        this.c.a(requestParameters);
        this.c.request();
    }

    protected void a(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.a(view, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected boolean a(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        return this.c.a(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void a(View view, IXAdInstanceInfo iXAdInstanceInfo, int i, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.a(view, iXAdInstanceInfo, i, iXAdFeedsRequestParameters);
    }

    protected void b(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.b(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void a(Context context, int i, int i2, IXAdInstanceInfo iXAdInstanceInfo) {
        this.c.a(context, i, i2, iXAdInstanceInfo);
    }

    protected void c(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.c(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void a(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.a(context, i, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void d(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.d(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void b(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.b(context, i, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    public static void setAppSid(Context context, String str) {
        XAdSDKFoundationFacade.getInstance().getCommonUtils().setAppId(str);
    }
}
