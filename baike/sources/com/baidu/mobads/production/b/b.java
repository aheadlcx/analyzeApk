package com.baidu.mobads.production.b;

import android.content.Context;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.a;
import com.baidu.mobads.production.u;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class b extends a implements IXNonLinearAdSlot {
    private d v;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return o();
    }

    public b(Context context, RelativeLayout relativeLayout, String str, String str2) {
        super(context);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_CPU;
        this.v = new d(getApplicationContext(), getActivity(), this.o, str, str2);
    }

    public void e() {
        load();
    }

    protected void f() {
        this.m = 10000;
    }

    public void request() {
        a(this.v);
        try {
            WebView webView = new WebView(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void b(d dVar) {
        this.k = dVar;
        i();
        a(null, null, 5000);
    }

    protected void a(c cVar, u uVar, int i) {
        try {
            setAdResponseInfo(new com.baidu.mobads.vo.c("{'ad':[{'id':99999999,'html':'" + this.v.c() + "', type='" + CreativeType.HTML.getValue() + "'}],'n':1}"));
            this.b = Boolean.valueOf(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        b("XAdMouldeLoader ad-server requesting success");
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public d o() {
        return this.v;
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.n();
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
