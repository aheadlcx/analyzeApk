package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;

final class as extends VLAsyncHandler<String> {
    final /* synthetic */ ResetPwdEngine a;

    as(ResetPwdEngine resetPwdEngine) {
        this.a = resetPwdEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                String str = (String) getParam();
                String[] split = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]")).replace("\"", "").split(",");
                String str2 = split[0];
                str = split[1];
                if (!str2.equals("1")) {
                    this.a.a.handleErrorInfo(str2, str);
                } else if (this.a.a != null) {
                    this.a.a.result("1");
                }
            } catch (Exception e) {
                this.a.a.error(CommonInts.STRING_OUTOFBOUNDS_EXCEPTION);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
