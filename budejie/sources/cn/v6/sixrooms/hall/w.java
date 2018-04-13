package cn.v6.sixrooms.hall;

final class w implements CallBack<HostsLocationBean> {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ void handleInfo(Object obj) {
        HostsLocationBean hostsLocationBean = (HostsLocationBean) obj;
        if (this.a.b != null) {
            this.a.b.getData(hostsLocationBean);
            this.a.b.hideLoading();
        }
    }

    public final void error(int i) {
        if (this.a.b != null) {
            this.a.b.hideLoading();
            this.a.b.handleError(i);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        if (this.a.b != null) {
            this.a.b.hideLoading();
            this.a.b.handleErrorInfo(str, str2);
        }
    }
}
