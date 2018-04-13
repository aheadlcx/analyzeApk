package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.engine.RefreshChatListEngine.CallBack;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import java.util.ArrayList;
import java.util.Collection;

final class ar implements CallBack {
    final /* synthetic */ SpectatorPage a;

    ar(SpectatorPage spectatorPage) {
        this.a = spectatorPage;
    }

    public final void resultInfo(WrapUserInfo wrapUserInfo) {
        if (this.a.isLoadingDate) {
            this.a.isLoadingDate = false;
            if (this.a.b == null) {
                this.a.m.onHeaderRefreshComplete();
                return;
            }
            WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
            if (localRoomInfo != null) {
                localRoomInfo.setWrapUserInfo(wrapUserInfo);
            }
            this.a.setSpectatorNum(wrapUserInfo.getNum());
            if (this.a.q == 31) {
                this.a.r.clear();
                this.a.t.clear();
                Collection allAdmList = wrapUserInfo.getAllAdmList();
                if (allAdmList == null) {
                    this.a.m.onHeaderRefreshComplete();
                    this.a.m.onFooterRefreshComplete();
                    return;
                }
                this.a.t.addAll(allAdmList);
                this.a.r.addAll(this.a.t);
                this.a.j.setText(" 管理员(" + this.a.r.size() + ")");
                this.a.w.notifyDataSetChanged();
            } else if (this.a.q == 32) {
                this.a.r.clear();
                this.a.u.clear();
                ArrayList allList = wrapUserInfo.getAllList();
                if (allList == null) {
                    this.a.m.onHeaderRefreshComplete();
                    this.a.m.onFooterRefreshComplete();
                    return;
                }
                this.a.u.addAll(SpectatorPage.b(allList));
                this.a.r.addAll(this.a.u);
                this.a.k.setText("观众(" + wrapUserInfo.getNum() + ")");
                this.a.w.notifyDataSetChanged();
            } else if (this.a.q == 30) {
                this.a.r.clear();
                this.a.v.clear();
                Object safeList = wrapUserInfo.getSafeList();
                if (safeList == null) {
                    this.a.m.onHeaderRefreshComplete();
                    return;
                }
                this.a.v.addAll(safeList);
                this.a.r.addAll(this.a.v);
                this.a.l.setText("守护(" + safeList.size() + ")");
                this.a.w.notifyDataSetChanged();
            }
            this.a.m.onHeaderRefreshComplete();
            this.a.m.onFooterRefreshComplete();
        }
    }

    public final void error(int i) {
        if (this.a.isLoadingDate) {
            this.a.m.onHeaderRefreshComplete();
            this.a.m.onFooterRefreshComplete();
            this.a.a.showErrorToast(i);
        }
    }
}
