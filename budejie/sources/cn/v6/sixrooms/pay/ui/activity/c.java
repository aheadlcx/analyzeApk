package cn.v6.sixrooms.pay.ui.activity;

import android.os.AsyncTask;
import android.os.SystemClock;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class c extends AsyncTask<Void, Void, Void> {
    String a = GlobleValue.getUserBean().getId();
    String b = SaveUserInfoUtils.getEncpass(this.c);
    final /* synthetic */ AlipayActivity c;

    c(AlipayActivity alipayActivity) {
        this.c = alipayActivity;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        for (int i = 0; i < 3 && this.c.o; i++) {
            publishProgress(new Void[0]);
            SystemClock.sleep(5000);
        }
        return null;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        super.onPostExecute((Void) obj);
        if (this.c.l.isShowing()) {
            this.c.l.dismiss();
            AlipayActivity.r(this.c);
        }
    }

    protected final /* synthetic */ void onProgressUpdate(Object[] objArr) {
        super.onProgressUpdate((Void[]) objArr);
        LogUtils.i("AlipayActivity", "开始请求充值结果");
        this.c.p.orderStatus(this.c.q, this.b, this.a);
    }
}
