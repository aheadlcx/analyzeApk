package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.mvp.interfaces.V6Viewable;

public class HostsPresenter {
    private HostsEngine a;
    private V6Viewable b;

    public HostsPresenter(V6Viewable v6Viewable) {
        this.b = v6Viewable;
    }

    public void getHosts(String str, int i, boolean z) {
        if (this.b != null && i == 1 && z) {
            this.b.showLoading();
        }
        if (this.a == null) {
            this.a = new HostsEngine(new ae(this));
        }
        this.a.getHosts(str, String.valueOf(i));
    }
}
