package com.baidu.mobads.production.c;

import android.app.Activity;
import android.content.Context;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class b extends d {
    private int a;
    private int j;
    private int k;

    public b(Context context, Activity activity, SlotType slotType) {
        super(context, activity, slotType);
        this.a = 1;
        this.j = 1;
        this.k = 1;
        this.b = this.i.replaceURLWithSupportProtocol("http://mobads.baidu.com/cpro/ui/mads.php");
        a("androidfeed");
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(IXAdRequestInfo.FET, "ANTI,MSSP,NMON,HTML,CLICK2VIDEO,PAUSE,VIDEO");
        hashMap.put("pos", "" + this.j);
        hashMap.put("seq", "" + this.k);
        hashMap.put("viewid", "" + this.a);
        return hashMap;
    }

    public void a(int i) {
        this.a = i;
    }

    public void b(int i) {
        this.j = i;
    }

    public void c(int i) {
        this.k = i;
    }

    public String b() {
        return super.b();
    }
}
