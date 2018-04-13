package qsbk.app.live.ui.family;

import android.support.v4.app.NotificationCompat;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.widget.FamilyCardDialog;

class u extends Callback {
    final /* synthetic */ FamilyDetailActivity a;

    u(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public void onFinished() {
        super.onFinished();
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            int simpleDataInt = baseResponse.getSimpleDataInt("expr");
            this.a.C = baseResponse.getSimpleDataInt("checkin_count");
            this.a.E = baseResponse.getSimpleDataInt("stay_checkin_count");
            this.a.N = baseResponse.getSimpleDataInt("member_count");
            this.a.aa = 1;
            new FamilyCardDialog(this.a, simpleDataInt).show();
            this.a.c();
        }
    }
}
