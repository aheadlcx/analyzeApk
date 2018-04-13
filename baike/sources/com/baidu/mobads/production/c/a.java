package com.baidu.mobads.production.c;

import android.content.Context;
import android.view.View;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.BaiduNativeH5AdView;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.feeds.IXAdDummyContainer;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.u;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a extends com.baidu.mobads.production.a {
    private b v = null;
    private BaiduNativeH5AdView w;

    public a(Context context, BaiduNativeH5AdView baiduNativeH5AdView) {
        super(context);
        this.w = baiduNativeH5AdView;
        setId(this.w.getAdPlacement().getApId());
        setActivity(context);
        setAdSlotBase(this.w);
        this.o = SlotType.SLOT_TYPE_FEEDS;
        this.v = new b(getApplicationContext(), getActivity(), this.o);
        this.v.d(this.w.getAdPlacement().getApId());
        g adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
        List arrayList = new ArrayList();
        arrayList.add(adConstants.getSupportedActionType4RequestingLandingPage());
        arrayList.add(adConstants.a());
        if (XAdSDKFoundationFacade.getInstance().getPackageUtils().a(this.f)) {
            arrayList.add(adConstants.getSupportedActionType4RequestingDownload());
        }
        this.v.b(XAdSDKFoundationFacade.getInstance().getCommonUtils().a(arrayList));
        this.v.h(0);
        this.v.f(AdSize.FeedH5TemplateNative.getValue());
        this.v.g(1);
    }

    public void b(int i) {
        this.v.a(i);
    }

    public void c(int i) {
        this.v.b(i);
    }

    public void d(int i) {
        this.v.c(i);
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width > 0 && height > 0) {
            this.v.d(width);
            this.v.e(height);
        }
    }

    public void request() {
        k();
        a(this.v);
    }

    public IXAdRequestInfo getAdRequestInfo() {
        return this.v;
    }

    protected void a(c cVar, u uVar, int i) {
        if (getAdResponseInfo() != null) {
            this.b = Boolean.valueOf(true);
            b("XAdMouldeLoader ad-server requesting success");
            return;
        }
        uVar.a(cVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        iXAdContainer.start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public void e() {
        if (this.h != null) {
            this.h.load();
        } else {
            this.r.e("container is null");
        }
    }

    protected void f() {
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
        if (iXAdResponseInfo.getAdInstanceList().size() <= 0) {
            return;
        }
        if (iXAdResponseInfo.getPrimaryAdInstanceInfo().getHtmlSnippet() == null || iXAdResponseInfo.getPrimaryAdInstanceInfo().getHtmlSnippet().length() <= 0) {
            a(XAdErrorCode.REQUEST_PARAM_ERROR, "代码位错误，请检查代码位是否是信息流模板");
            return;
        }
        this.w.getAdPlacement().setAdResponse(iXAdResponseInfo);
        dispatchEvent(new com.baidu.mobads.f.a("AdLoadData"));
    }

    public void o() {
    }

    public void a(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onImpression(view, iXAdInstanceInfo, iXAdFeedsRequestParameters, new HashMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
