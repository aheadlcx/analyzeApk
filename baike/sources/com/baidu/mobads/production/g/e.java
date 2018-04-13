package com.baidu.mobads.production.g;

import android.content.Context;
import android.os.Build.VERSION;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.g;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class e extends d {
    g a;

    public e(Context context, SlotType slotType) {
        super(context, null, slotType);
        this.a = XAdSDKFoundationFacade.getInstance().getAdConstants();
        this.b = this.i.replaceURLWithSupportProtocol("http://mobads.baidu.com/cpro/ui/mads.php");
        g(1);
        if (c()) {
            i((this.a.getAdCreativeTypeImage() + this.a.getAdCreativeTypeVideo()) + this.a.getAdCreativeTypeRichmedia());
        } else {
            i(this.a.getAdCreativeTypeImage() + this.a.getAdCreativeTypeVideo());
        }
        f(8);
        h(0);
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(IXAdRequestInfo.FET, "ANTI,HTML,MSSP,VIDEO,RSPLASHHTML");
        return hashMap;
    }

    public String b() {
        return super.b();
    }

    private boolean c() {
        try {
            Class.forName("com.baidu.vr.vrplayer.VrImageView");
            Class.forName("com.baidu.vr.vrplayer.VrImageView$OnGestureListener");
            Class.forName("com.baidu.vr.vrplayer.VrImageView$OnBitmapLoadedListener");
            if (VERSION.SDK_INT >= 16) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            l.a().d(e);
            return false;
        }
    }
}
