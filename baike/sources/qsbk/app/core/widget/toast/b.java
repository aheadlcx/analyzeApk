package qsbk.app.core.widget.toast;

class b implements Runnable {
    final /* synthetic */ CustomToast$a a;

    b(CustomToast$a customToast$a) {
        this.a = customToast$a;
    }

    public void run() {
        this.a.handleHide();
        this.a.i = null;
    }
}
