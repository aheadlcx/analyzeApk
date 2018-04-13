package qsbk.app.activity.pay;

import android.os.Bundle;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.app.statistic.c;

class y implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ItemUnivasualBuyActivity b;

    y(ItemUnivasualBuyActivity itemUnivasualBuyActivity, String str) {
        this.b = itemUnivasualBuyActivity;
        this.a = str;
    }

    public void run() {
        PayTask payTask = new PayTask(this.b);
        if (this.a != null && this.b.u != null) {
            try {
                String pay = payTask.pay(this.a, false);
                Message message = new Message();
                message.what = 1;
                message.obj = pay;
                if (this.b.u != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(c.G, this.b.u);
                    message.setData(bundle);
                }
                this.b.q.sendMessage(message);
            } catch (Exception e) {
                this.b.q.post(new z(this));
            }
        }
    }
}
