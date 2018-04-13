package cn.v6.sixrooms.pay.ui.activity;

import android.os.Message;
import com.alipay.sdk.app.PayTask;

final class m implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ AlipayActivity b;

    m(AlipayActivity alipayActivity, String str) {
        this.b = alipayActivity;
        this.a = str;
    }

    public final void run() {
        try {
            String pay = new PayTask(this.b).pay(this.a, true);
            Message message = new Message();
            message.what = 1;
            message.obj = pay;
            this.b.v.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
