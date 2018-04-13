package cn.v6.sixrooms.room.presenter;

import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.engine.RoomInfoEngine.CallBack;
import cn.v6.sixrooms.room.presenter.InroomPresenter.Inroomable;
import java.util.Iterator;

final class d implements CallBack {
    final /* synthetic */ InroomPresenter a;

    d(InroomPresenter inroomPresenter) {
        this.a = inroomPresenter;
    }

    public final void rtmpURL(String str) {
        if (InroomPresenter.a(this.a) != null && InroomPresenter.b(this.a)) {
            InroomPresenter.a(this.a).setRtmpURL(str);
        }
    }

    public final void error(int i) {
        Iterator it = InroomPresenter.c(this.a).iterator();
        while (it.hasNext()) {
            ((Inroomable) it.next()).error(i);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        Iterator it = InroomPresenter.c(this.a).iterator();
        while (it.hasNext()) {
            ((Inroomable) it.next()).handlerError(str, str2);
        }
    }

    public final void resultInfo(WrapRoomInfo wrapRoomInfo) {
        Iterator it;
        if (wrapRoomInfo == null) {
            it = InroomPresenter.c(this.a).iterator();
            while (it.hasNext()) {
                ((Inroomable) it.next()).error(0);
            }
            return;
        }
        InroomPresenter.a(this.a, wrapRoomInfo);
        it = InroomPresenter.c(this.a).iterator();
        while (it.hasNext()) {
            ((Inroomable) it.next()).setWrapRoomInfo(wrapRoomInfo);
        }
        if (InroomPresenter.d(this.a) != null) {
            InroomPresenter.d(this.a).createSocket(wrapRoomInfo);
        }
    }

    public final void getPriv(String str) {
        if (InroomPresenter.e(this.a) != null) {
            InroomPresenter.e(this.a).setIsUserSafe(str);
        }
        Iterator it = InroomPresenter.c(this.a).iterator();
        while (it.hasNext()) {
            ((Inroomable) it.next()).setPriv(str);
        }
    }

    public final void getMicroIP_PORT(String str, String str2) {
    }
}
