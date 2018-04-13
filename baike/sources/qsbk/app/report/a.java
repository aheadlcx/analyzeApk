package qsbk.app.report;

import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class a extends Handler {
    final /* synthetic */ ArticleReporter a;

    a(ArticleReporter articleReporter) {
        this.a = articleReporter;
    }

    public void handleMessage(Message message) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(1)).show();
    }
}
