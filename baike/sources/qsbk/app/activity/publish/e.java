package qsbk.app.activity.publish;

class e implements Runnable {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void run() {
        this.a.a.circleScroll.fullScroll(130);
        this.a.a.n.requestFocus();
        this.a.a.n.setSelection(this.a.a.n.getText().length());
    }
}
