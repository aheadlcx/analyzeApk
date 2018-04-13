package com.budejie.www.activity.view;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.budejie.www.util.aa;
import com.umeng.onlineconfig.OnlineConfigAgent;

class a$1 extends Handler {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case -5:
                Toast.makeText(a.c(this.a), "录音失败,请重新尝试", 0).show();
                return;
            case -4:
                Toast.makeText(a.c(this.a), "初始化失败,采样率手机不支持", 0).show();
                return;
            case -3:
                Toast.makeText(a.c(this.a), "录音失败,请重新尝试", 0).show();
                return;
            case -1:
                Toast.makeText(a.c(this.a), "初始化失败,采样率手机不支持", 0).show();
                return;
            case 1:
                aa.e("wuzhenlin", "MSG_REC_STARTED");
                if (a.a(this.a) != null) {
                    a.a(this.a).sendEmptyMessage(21);
                    return;
                }
                return;
            case 2:
                aa.e("wuzhenlin", "MSG_REC_STOPPED");
                if (a.a(this.a) != null) {
                    a.a(this.a).removeMessages(21);
                    a.a(this.a).sendEmptyMessage(22);
                }
                if (a.b(this.a) != null) {
                    a.b(this.a).a();
                }
                a.a(this.a, true);
                return;
            case 5:
                int intValue = ((Integer) message.obj).intValue();
                aa.e("wuzhenlin", "MSG_REC_DIFF value = " + intValue);
                a.d(this.a).setAF(intValue);
                if (a.e(this.a) == 1) {
                    a.a(this.a, intValue);
                    return;
                }
                String configParams = OnlineConfigAgent.getInstance().getConfigParams(a.c(this.a), "sound_commend_msg");
                if (TextUtils.isEmpty(configParams)) {
                    configParams = "骂人会被封号哦";
                }
                this.a.a(configParams);
                return;
            case 10:
                a.d();
                if (a.e() < a.f(this.a)) {
                    a.g(this.a).sendEmptyMessageDelayed(10, 1000);
                    return;
                }
                this.a.cancel();
                Toast.makeText(a.c(this.a), "本次录音时间" + a.f(this.a) + "s已用完", 1).show();
                return;
            default:
                return;
        }
    }
}
