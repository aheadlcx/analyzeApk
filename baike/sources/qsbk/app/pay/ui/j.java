package qsbk.app.pay.ui;

import android.os.Bundle;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.app.statistic.c;

class j implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ float c;
    final /* synthetic */ float d;
    final /* synthetic */ String e;
    final /* synthetic */ int f;
    final /* synthetic */ PayActivity g;

    j(PayActivity payActivity, String str, String str2, float f, float f2, String str3, int i) {
        this.g = payActivity;
        this.a = str;
        this.b = str2;
        this.c = f;
        this.d = f2;
        this.e = str3;
        this.f = i;
    }

    public void run() {
        PayTask payTask = new PayTask(this.g);
        if (this.a == null || this.b == null) {
            this.g.l.post(new k(this));
            return;
        }
        try {
            String pay = payTask.pay(this.a, false);
            Message message = new Message();
            message.what = 1;
            message.obj = pay;
            if (this.b != null) {
                Bundle bundle = new Bundle();
                bundle.putString(c.G, this.b);
                bundle.putLong("amount", ((long) (this.c + this.d)) * 10);
                message.setData(bundle);
            }
            this.g.l.sendMessage(message);
        } catch (Exception e) {
            this.g.a("alipay", this.e, this.f, e.getMessage());
            this.g.l.post(new l(this));
        }
    }
}
