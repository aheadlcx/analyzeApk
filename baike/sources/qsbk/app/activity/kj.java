package qsbk.app.activity;

import android.util.Pair;
import qsbk.app.QsbkApp;
import qsbk.app.api.UserHeaderHelper.UploadAvatarTask;
import qsbk.app.utils.ToastAndDialog;

class kj extends UploadAvatarTask {
    final /* synthetic */ EditInfoEntranceActivity a;

    kj(EditInfoEntranceActivity editInfoEntranceActivity, String str) {
        this.a = editInfoEntranceActivity;
        super(str);
    }

    protected void a(Pair<Integer, String> pair) {
        super.a(pair);
        if (((Integer) pair.first).equals(Integer.valueOf(0))) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, (String) pair.second).show();
            if (EditInfoEntranceActivity.a(this.a).getPickedBitmap() != null) {
                EditInfoEntranceActivity.b(this.a).setImageBitmap(EditInfoEntranceActivity.a(this.a).getPickedBitmap());
                return;
            }
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) pair.second).show();
    }
}
