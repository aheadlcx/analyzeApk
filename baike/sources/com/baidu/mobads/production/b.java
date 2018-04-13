package com.baidu.mobads.production;

import com.baidu.mobads.a.a;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.c;
import org.json.JSONException;

class b implements IOAdEventListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run(IOAdEvent iOAdEvent) {
        String str;
        this.a.i();
        if ("URLLoader.Load.Complete".equals(iOAdEvent.getType())) {
            try {
                this.a.setAdResponseInfo(new c((String) iOAdEvent.getData().get("message")));
                if (this.a.w == null || this.a.w.getAdInstanceList().size() <= 0) {
                    XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage(this.a.getAdResponseInfo().getErrorCode(), this.a.getAdResponseInfo().getErrorMessage(), "");
                    this.a.c(this.a.getAdResponseInfo().getErrorMessage());
                    return;
                }
                a.n = System.currentTimeMillis();
                this.a.b = Boolean.valueOf(true);
                this.a.a(this.a.w);
                this.a.b("XAdMouldeLoader ad-server requesting success");
                return;
            } catch (JSONException e) {
                str = "response json parsing error";
                XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
                this.a.c(str);
                com.baidu.mobads.c.a.a().a(str);
                return;
            }
        }
        str = "request ad-server error, io_err/timeout";
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
        this.a.c(str);
        com.baidu.mobads.c.a.a().a(str);
    }
}
