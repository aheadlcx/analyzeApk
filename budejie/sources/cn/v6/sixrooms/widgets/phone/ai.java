package cn.v6.sixrooms.widgets.phone;

final class ai implements Runnable {
    final /* synthetic */ ShowGuardPopWindow a;

    ai(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void run() {
        ShowGuardPopWindow.i(this.a);
    }
}
