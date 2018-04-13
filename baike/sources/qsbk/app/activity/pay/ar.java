package qsbk.app.activity.pay;

import android.os.Bundle;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.app.statistic.c;

class ar implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ aq b;

    ar(aq aqVar, String str) {
        this.b = aqVar;
        this.a = str;
    }

    public void run() {
        PayTask payTask = new PayTask(this.b.a);
        if (this.a != null && this.b.a.x != null) {
            try {
                String pay = payTask.pay(this.a, false);
                Message message = new Message();
                message.what = 1;
                message.obj = pay;
                if (this.b.a.x != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(c.G, this.b.a.x);
                    message.setData(bundle);
                }
                this.b.a.E.sendMessage(message);
            } catch (Exception e) {
                this.b.a.E.post(new as(this));
            }
        }
    }
}
