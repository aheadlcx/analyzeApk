package qsbk.app.live.ui.family;

import org.json.JSONObject;
import qsbk.app.core.net.upload.IUploadListener;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class l implements IUploadListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ FamilyCreateActivity c;

    l(FamilyCreateActivity familyCreateActivity, String str, String str2) {
        this.c = familyCreateActivity;
        this.a = str;
        this.b = str2;
    }

    public void uploadStat(String str, boolean z, String str2, JSONObject jSONObject) {
        this.c.hideSavingDialog();
        if (z) {
            this.c.showSnackbar(this.c.getString(R.string.user_avatar_upload_success));
            this.c.c = this.a;
            this.c.b = str;
            this.c.i.postDelayed(new m(this), 500);
            return;
        }
        ToastUtil.Short(str2);
    }

    public void uploadProgress(String str, double d) {
    }
}
