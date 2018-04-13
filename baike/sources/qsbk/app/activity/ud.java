package qsbk.app.activity;

import qsbk.app.utils.ToastAndDialog;

class ud implements Runnable {
    final /* synthetic */ MessageInfoReminderActivity a;

    ud(MessageInfoReminderActivity messageInfoReminderActivity) {
        this.a = messageInfoReminderActivity;
    }

    public void run() {
        try {
            this.a.c.deleteAllMessages();
            this.a.d.deleteAllMessages();
            this.a.e.deleteAllMessages();
            this.a.f.deleteAll();
            ToastAndDialog.makePositiveToast(this.a.getApplicationContext(), "已清空小纸条记录！").show();
        } catch (Throwable th) {
        }
    }
}
