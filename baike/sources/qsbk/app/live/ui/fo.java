package qsbk.app.live.ui;

class fo implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ NetworkDiagnosisActivity b;

    fo(NetworkDiagnosisActivity networkDiagnosisActivity, String str) {
        this.b = networkDiagnosisActivity;
        this.a = str;
    }

    public void run() {
        this.b.a.getText().append(this.a + "\n");
        this.b.a.setSelection(this.b.a.getText().length(), this.b.a.getText().length());
    }
}
