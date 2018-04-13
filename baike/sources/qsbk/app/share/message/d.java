package qsbk.app.share.message;

import android.os.Message;

class d extends Thread {
    final /* synthetic */ MyDialog a;

    d(MyDialog myDialog) {
        this.a = myDialog;
    }

    public void run() {
        super.run();
        while (this.a.b) {
            try {
                Thread.sleep(3000);
                Message obtainMessage = this.a.c.obtainMessage();
                obtainMessage.what = this.a.a;
                this.a.c.sendMessage(obtainMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
