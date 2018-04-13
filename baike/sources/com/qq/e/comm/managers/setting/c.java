package com.qq.e.comm.managers.setting;

import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import org.json.JSONObject;

public class c {
    private JSONObject a;

    public c() {
        this.a = new JSONObject();
    }

    public c(String str) {
        this();
        GDTLogger.d("Initialize GDTSDKSetting,Json=" + str);
        if (!StringUtil.isEmpty(str)) {
            try {
                this.a = new JSONObject(str);
            } catch (Throwable e) {
                GDTLogger.report("Exception while building GDTSDKSetting from json", e);
            }
        }
    }

    final Object a(String str) {
        return this.a.opt(str);
    }

    final void a(String str, Object obj) {
        try {
            this.a.putOpt(str, obj);
        } catch (Throwable e) {
            GDTLogger.e("Exception while update setting", e);
        }
    }

    public String toString() {
        return "GDTSDKSetting[" + this.a.toString() + "]";
    }
}
