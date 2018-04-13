package qsbk.app.core.widget.toast;

class a implements Runnable {
    final /* synthetic */ CustomToast$a a;

    a(CustomToast$a customToast$a) {
        this.a = customToast$a;
    }

    public void run() {
        this.a.handleShow();
    }
}
