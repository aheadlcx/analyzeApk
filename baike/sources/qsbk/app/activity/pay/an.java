package qsbk.app.activity.pay;

import android.os.Bundle;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.app.statistic.c;

class an implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ am b;

    an(am amVar, String str) {
        this.b = amVar;
        this.a = str;
    }

    public void run() {
        PayTask payTask = new PayTask(this.b.b);
        if (this.a != null && this.b.b.x != null) {
            try {
                String pay = payTask.pay(this.a, false);
                Message message = new Message();
                message.what = 1;
                message.obj = pay;
                if (this.b.b.x != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(c.G, this.b.b.x);
                    message.setData(bundle);
                }
                this.b.b.E.sendMessage(message);
            } catch (Exception e) {
                this.b.b.E.post(new ao(this));
            }
        }
    }
}
