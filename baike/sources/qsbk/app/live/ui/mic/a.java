package qsbk.app.live.ui.mic;

class a implements Runnable {
    final /* synthetic */ MicConnectDialog a;

    a(MicConnectDialog micConnectDialog) {
        this.a = micConnectDialog;
    }

    public void run() {
        this.a.dismiss();
    }
}
