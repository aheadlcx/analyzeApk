package com.baidu.mobads.production.f;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.a;
import com.baidu.mobads.production.u;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a implements a {
    private boolean A = false;
    private Activity B;
    private RelativeLayout C;
    private Boolean D;
    public final String v = "html5_intersitial";
    protected final IXAdLogger w = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private d x;
    private AdSize y;
    private boolean z = false;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return p();
    }

    public void e() {
    }

    public b(Context context, RelativeLayout relativeLayout, Boolean bool, String str) {
        JSONObject jSONObject;
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_INTERSTITIAL;
        this.D = bool;
        this.y = AdSize.InterstitialGame;
        XAdSDKFoundationFacade.getInstance().getAdConstants();
        this.x = new d(getApplicationContext(), getActivity(), this.o, Boolean.valueOf(true));
        this.x.d(str);
        this.x.f(AdSize.InterstitialGame.getValue());
        com.baidu.mobads.vo.b bVar = (com.baidu.mobads.vo.b) this.x.d();
        JSONObject attribute = bVar.getAttribute();
        if (attribute == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = attribute;
        }
        try {
            jSONObject.put("ABILITY", "PAUSE,");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bVar.a(jSONObject);
        d(str);
    }

    protected void f() {
        this.m = 8000;
    }

    public void request() {
        super.a(this.x);
    }

    protected void a(c cVar, u uVar, int i) {
        uVar.a(cVar, "{'ad':[{'id':99999999,'url':'" + this.x.b() + "', type='" + CreativeType.HTML.getValue() + "'}],'n':1}");
    }

    public void start() {
        super.start();
    }

    public void a(Activity activity) {
        if (this.z && !this.A) {
            this.A = true;
            this.z = false;
            this.B = activity;
            start();
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            this.e.setBackgroundColor(0);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.topMargin = rect.top;
            ViewGroup c = c((Context) activity);
            this.C = new RelativeLayout(activity);
            this.C.addView(this.e, layoutParams);
            c.addView(this.C, new RelativeLayout.LayoutParams(-1, -1));
            this.e.setFocusableInTouchMode(true);
            this.e.setFocusable(true);
            this.e.requestFocus();
        } else if (this.A) {
            this.w.w("interstitial ad is showing now");
        } else if (!this.z) {
            this.w.w("interstitial ad is not ready");
        }
    }

    public void a(Activity activity, RelativeLayout relativeLayout) {
    }

    private ViewGroup c(Context context) {
        return (ViewGroup) ((Activity) context).getWindow().getDecorView();
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.z = true;
        if (hashMap != null) {
            Object obj = hashMap.get("type");
            if (obj != null && !((String) obj).equals("video")) {
            }
        }
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public d p() {
        return this.x;
    }

    public void q() {
        if (this.B != null) {
            this.B.runOnUiThread(new c(this));
        }
    }

    public boolean t() {
        return this.z;
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        q();
        this.A = false;
    }

    public void o() {
        load();
    }

    public void a(int i, int i2) {
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
