package qsbk.app.live.ui.family;

import android.support.v4.app.NotificationCompat;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.R;

class i extends Callback {
    final /* synthetic */ FamilyCreateActivity a;

    i(FamilyCreateActivity familyCreateActivity) {
        this.a = familyCreateActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            if (baseResponse.getSimpleDataInt("create_cost") > 0) {
                this.a.k.setText(this.a.getString(R.string.family_create_con, new Object[]{r0 + this.a.getString(R.string.live_diamond)}));
                return;
            }
            this.a.k.setText(this.a.getString(R.string.family_create_con, new Object[]{this.a.getString(R.string.family_create_free)}));
        }
    }
}
