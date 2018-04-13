package com.baidu.mobads.vo.a;

import android.content.Context;
import com.ali.auth.third.core.model.Constants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.HashMap;
import org.apache.commons.httpclient.HttpState;

public class c extends a {
    public boolean o = true;
    private Context p;
    private IXAppInfo q;

    public c(Context context, IXAppInfo iXAppInfo) {
        super(iXAppInfo.getAdId(), iXAppInfo.getQk(), iXAppInfo.getProd());
        this.p = context;
        this.q = iXAppInfo;
    }

    protected HashMap<String, String> b() {
        String str = "" + XAdSDKFoundationFacade.getInstance().getSystemUtils().getCurrentProcessId(this.p);
        HashMap<String, String> hashMap = new HashMap();
        try {
            hashMap.put("autoOpen", this.o ? Constants.SERVICE_SCOPE_FLAG_VALUE : HttpState.PREEMPTIVE_DEFAULT);
            hashMap.put(IXAdRequestInfo.PACKAGE, this.q.getPackageName());
            hashMap.put("c", "0");
            hashMap.put("clickProcId", str);
            hashMap.put("clickTime", this.q.getClickTime() + "");
            hashMap.put("contentLength", "" + this.q.getAppSize());
            hashMap.put("dlCnt", "1");
            hashMap.put("dlTime", String.valueOf(System.currentTimeMillis() - this.q.getClickTime()));
            hashMap.put("dlTunnel", "3");
            hashMap.put("dlWay", this.q.isTooLarge() ? "1" : "0");
            hashMap.put("exp_id", "");
            hashMap.put("exp2", "");
            hashMap.put("pkg", this.q.getPackageName());
            hashMap.put("typeProcId", str);
            hashMap.put("url2", "");
            hashMap.put(IXAdRequestInfo.SN, XAdSDKFoundationFacade.getInstance().getBase64().encode(XAdSDKFoundationFacade.getInstance().getSystemUtils().getIMEI(this.p)));
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
        }
        return hashMap;
    }
}
