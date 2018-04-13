package cn.v6.sixrooms.pay.ui.activity;

import android.os.AsyncTask;
import android.os.SystemClock;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class as extends AsyncTask<Void, Void, Void> {
    String a = GlobleValue.getUserBean().getId();
    String b = SaveUserInfoUtils.getEncpass(this.d);
    final /* synthetic */ String c;
    final /* synthetic */ MobilePayActivity d;

    as(MobilePayActivity mobilePayActivity, String str) {
        this.d = mobilePayActivity;
        this.c = str;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        for (int i = 0; i < 12 && this.d.k; i++) {
            publishProgress(new Void[0]);
            SystemClock.sleep(5000);
        }
        return null;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        super.onPostExecute((Void) obj);
        if (this.d.p.isShowing() && this.d.k) {
            this.d.dissSubmitDialog();
            MobilePayActivity.l(this.d);
        }
    }

    protected final /* synthetic */ void onProgressUpdate(Object[] objArr) {
        super.onProgressUpdate((Void[]) objArr);
        this.d.s.payCardStatus(this.c, this.b, this.a);
    }
}
