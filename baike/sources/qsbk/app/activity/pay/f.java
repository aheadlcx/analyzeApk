package qsbk.app.activity.pay;

import android.os.Bundle;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.app.statistic.c;

class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ e b;

    f(e eVar, String str) {
        this.b = eVar;
        this.a = str;
    }

    public void run() {
        PayTask payTask = new PayTask(this.b.a);
        if (this.a != null && this.b.a.u != null) {
            try {
                String pay = payTask.pay(this.a, false);
                Message message = new Message();
                message.what = 1;
                message.obj = pay;
                if (this.b.a.u != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(c.G, this.b.a.u);
                    message.setData(bundle);
                }
                this.b.a.mHandler.sendMessage(message);
            } catch (Exception e) {
                this.b.a.mHandler.post(new g(this));
            }
        }
    }
}
