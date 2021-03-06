package com.baidu.mobads.production;

import com.baidu.mobads.c.a;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.c;
import org.json.JSONObject;

class b implements IOAdEventListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run(IOAdEvent iOAdEvent) {
        String str;
        this.a.k();
        if ("URLLoader.Load.Complete".equals(iOAdEvent.getType())) {
            try {
                this.a.setAdResponseInfo(new c((String) iOAdEvent.getData().get(com.baidu.mobads.openad.c.b.EVENT_MESSAGE)));
                if (this.a.x == null || this.a.x.getAdInstanceList().size() <= 0) {
                    XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage(this.a.getAdResponseInfo().getErrorCode(), this.a.getAdResponseInfo().getErrorMessage(), "");
                    this.a.d(this.a.getAdResponseInfo().getErrorMessage());
                    return;
                }
                this.a.d = this.a.x.getPrimaryAdInstanceInfo();
                JSONObject originJsonObject = this.a.d.getOriginJsonObject();
                this.a.r = originJsonObject.optString("mimetype");
                this.a.q();
                this.a.a();
                return;
            } catch (Exception e) {
                str = "response json parsing error";
                XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
                this.a.d(str);
                a.a().a(str);
                return;
            }
        }
        str = "request ad-server error, io_err/timeout";
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
        this.a.d(str);
        a.a().a(str);
    }
}
