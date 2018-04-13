package qsbk.app.live.ui;

class fm extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ NetworkDiagnosisActivity b;

    fm(NetworkDiagnosisActivity networkDiagnosisActivity, String str) {
        this.b = networkDiagnosisActivity;
        this.a = str;
    }

    public void run() {
        this.b.a(this.a);
        this.b.mHandler.post(new fn(this));
    }
}
