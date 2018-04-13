package com.baidu.mobads.production.i;

import android.content.Context;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.production.c.c;
import com.baidu.mobads.vo.d;

public class a extends c {
    private com.baidu.mobads.production.h.a v;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return p();
    }

    public a(Context context, String str) {
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(null);
        this.o = SlotType.SLOT_TYPE_PREROLL;
        this.v = new com.baidu.mobads.production.h.a(getApplicationContext(), getActivity(), SlotType.SLOT_TYPE_PREROLL, this);
        this.v.f(AdSize.PrerollVideoNative.getValue());
        this.v.d(str);
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width > 0 && height > 0) {
            this.v.d(width);
            this.v.e(height);
        }
    }

    public d p() {
        return this.v;
    }

    public void request() {
        super.a(this.v);
    }
}
