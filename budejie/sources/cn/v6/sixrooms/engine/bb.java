package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;

final class bb extends VLAsyncHandler<String> {
    final /* synthetic */ RoomInfoEngine a;

    bb(RoomInfoEngine roomInfoEngine) {
        this.a = roomInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            String str = (String) getParam();
            try {
                str = str.substring(str.indexOf("<watchip>") + 9, str.indexOf("</watchip>"));
                if (!str.contains(":")) {
                    str = str + ":8080";
                }
                this.a.a.rtmpURL(str);
            } catch (Exception e) {
                e.printStackTrace();
                this.a.a.error(1006);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
