package qsbk.app.core.widget.toast;

class c implements Runnable {
    final /* synthetic */ SystemToastReflection a;

    c(SystemToastReflection systemToastReflection) {
        this.a = systemToastReflection;
    }

    public void run() {
        this.a.cancel();
    }
}
