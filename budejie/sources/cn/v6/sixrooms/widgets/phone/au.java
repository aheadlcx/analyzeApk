package cn.v6.sixrooms.widgets.phone;

final class au implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ SpectatorPage b;

    au(SpectatorPage spectatorPage, String str) {
        this.b = spectatorPage;
        this.a = str;
    }

    public final void run() {
        if (this.b.r != null && this.b.m.isShown()) {
            if (this.b.q == 31) {
                this.b.r.clear();
                this.b.r.addAll(this.b.t);
                this.b.j.setText(" 管理员(" + this.b.r.size() + ")");
                this.b.w.notifyDataSetChanged();
            } else if (this.b.q == 32) {
                this.b.r.clear();
                this.b.r.addAll(this.b.u);
                this.b.k.setText("观众(" + this.a + ")");
                this.b.setSpectatorNum(this.a);
                this.b.w.notifyDataSetChanged();
            }
            this.b.m.onHeaderRefreshComplete();
            this.b.m.onFooterRefreshComplete();
        }
    }
}
