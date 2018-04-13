package com.baidu.mobads.production.d;

import android.webkit.WebView;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXHybridAdRenderer;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.u;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import org.json.JSONException;

public class a extends com.baidu.mobads.production.a implements IXNonLinearAdSlot {
    private c v;
    private IXHybridAdRenderer w;
    private WebView x;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return o();
    }

    public a(WebView webView) {
        super(webView.getContext());
        this.x = webView;
        setActivity(webView.getContext());
        this.o = SlotType.SLOT_TYPE_JSSDK;
        this.v = new c(getApplicationContext(), getActivity(), this.o);
    }

    public void e() {
        load();
    }

    protected void f() {
        this.m = 10000;
    }

    public void request() {
        a(this.v);
    }

    protected void b(d dVar) {
        this.k = dVar;
        i();
        a(null, null, 5000);
    }

    protected void a(c cVar, u uVar, int i) {
        String str = "{'ad':[{'id':99999999,'url':'" + this.v.b() + "', type='" + CreativeType.HYBRID.getValue() + "'}],'n':1}";
        this.b = Boolean.valueOf(true);
        try {
            setAdResponseInfo(new com.baidu.mobads.vo.c(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b("XAdMouldeLoader ad-server requesting success");
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        try {
            this.w = (IXHybridAdRenderer) this.h;
            this.w.setCustomerWebView(this.x);
        } catch (Exception e) {
            this.w = null;
        }
        start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public d o() {
        return this.v;
    }

    protected void b() {
        new Thread(new b(this)).start();
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.n();
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }

    public boolean a(WebView webView, String str) {
        return this.w == null ? false : this.w.shouldOverrideUrlLoading(webView, str);
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
