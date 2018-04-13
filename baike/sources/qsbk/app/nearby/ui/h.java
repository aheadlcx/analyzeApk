package qsbk.app.nearby.ui;

import android.util.Pair;
import qsbk.app.api.UserHeaderHelper.UploadAvatarTask;
import qsbk.app.utils.ToastAndDialog;

class h extends UploadAvatarTask {
    final /* synthetic */ String a;
    final /* synthetic */ InfoCompleteActivity b;

    h(InfoCompleteActivity infoCompleteActivity, String str, String str2) {
        this.b = infoCompleteActivity;
        this.a = str2;
        super(str);
    }

    protected void a(Pair<Integer, String> pair) {
        super.a(pair);
        this.b.G();
        if (((Integer) pair.first).equals(Integer.valueOf(0))) {
            this.b.i.postDelayed(new i(this), 50);
            return;
        }
        ToastAndDialog.makeNegativeToast(this.b, (String) pair.second).show();
        if (((Integer) pair.first).intValue() == 9999) {
            this.b.l(this.a);
        }
    }
}
