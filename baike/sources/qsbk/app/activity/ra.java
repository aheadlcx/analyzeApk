package qsbk.app.activity;

class ra implements Runnable {
    final /* synthetic */ qz a;

    ra(qz qzVar) {
        this.a = qzVar;
    }

    public void run() {
        this.a.b.setEnabled(true);
    }
}
