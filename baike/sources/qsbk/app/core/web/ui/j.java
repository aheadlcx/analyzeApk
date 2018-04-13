package qsbk.app.core.web.ui;

class j implements Runnable {
    final /* synthetic */ WebActivity$GetWebShareInfo a;

    j(WebActivity$GetWebShareInfo webActivity$GetWebShareInfo) {
        this.a = webActivity$GetWebShareInfo;
    }

    public void run() {
        this.a.a.c.setVisibility(this.a.a.h != null ? 0 : 8);
    }
}
