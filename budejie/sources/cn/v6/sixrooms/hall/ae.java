package cn.v6.sixrooms.hall;

import java.util.List;

final class ae implements CallBack<List<LiveItemBean>> {
    final /* synthetic */ HostsPresenter a;

    ae(HostsPresenter hostsPresenter) {
        this.a = hostsPresenter;
    }

    public final /* synthetic */ void handleInfo(Object obj) {
        List list = (List) obj;
        if (this.a.b != null) {
            this.a.b.getData(list);
            this.a.b.hideLoading();
        }
    }

    public final void error(int i) {
        if (this.a.b != null) {
            this.a.b.handleError(i);
            this.a.b.hideLoading();
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        if (this.a.b != null) {
            this.a.b.hideLoading();
            this.a.b.handleErrorInfo(str, str2);
        }
    }
}
