package com.baidu.mobads.vo.a;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.vo.a.a.a;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.Map;

public class d extends a {
    private String o = "";
    private HashMap<String, String> p = null;

    public d(String str, IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo, HashMap<String, String> hashMap) {
        super(new a(iXAdInstanceInfo, iXAdProdInfo));
        this.o = str;
        this.p = hashMap;
    }

    protected HashMap<String, String> b() {
        if (this.p == null) {
            this.p = new HashMap();
        }
        return this.p;
    }

    public String a(Context context) {
        Map c = c();
        try {
            StringBuilder stringBuilder = new StringBuilder("type=" + this.o + com.alipay.sdk.sys.a.b);
            StringBuilder stringBuilder2 = new StringBuilder();
            IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            for (String str : c.keySet()) {
                String str2;
                String str3 = (String) c.get(str2);
                if (!(str2 == null || str3 == null)) {
                    str2 = commonUtils.encodeURIComponent(str2);
                    str3 = commonUtils.encodeURIComponent(str3);
                    stringBuilder.append(str2);
                    stringBuilder.append("=");
                    stringBuilder.append(str3);
                    stringBuilder.append(com.alipay.sdk.sys.a.b);
                    stringBuilder2.append(str3);
                    stringBuilder2.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
            }
            return "https://mobads-logs.baidu.com/dz.zb" + "?" + stringBuilder.toString();
        } catch (Throwable e) {
            l.a().d(e);
            return "";
        }
    }
}
