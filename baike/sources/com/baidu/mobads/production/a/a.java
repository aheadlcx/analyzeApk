package com.baidu.mobads.production.a;

import android.content.Context;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.u;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.b;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends com.baidu.mobads.production.a implements IXNonLinearAdSlot {
    private c v;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return o();
    }

    public a(Context context, RelativeLayout relativeLayout, String str, boolean z) {
        JSONObject jSONObject;
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_BANNER;
        XAdSDKFoundationFacade.getInstance().getAdConstants();
        this.v = new c(getApplicationContext(), getActivity(), this.o);
        this.v.f(AdSize.Banner.getValue());
        this.v.d(str);
        b bVar = (b) this.v.d();
        bVar.a(z);
        JSONObject attribute = bVar.getAttribute();
        if (attribute == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = attribute;
        }
        try {
            jSONObject.put("ABILITY", "BANNER_CLOSE,PAUSE,UNLIMITED_BANNER_SIZE,");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bVar.a(jSONObject);
        d(str);
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
            setAdResponseInfo(new com.baidu.mobads.vo.c("{'ad':[{'id':99999999,'url':'" + this.v.b() + "', type='" + CreativeType.HTML.getValue() + "'}],'n':1}"));
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

    protected void b() {
        new Thread(new b(this)).start();
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.n();
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
