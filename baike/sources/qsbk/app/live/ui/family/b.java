package qsbk.app.live.ui.family;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;

class b extends Callback {
    final /* synthetic */ FamilyBlankActivity a;

    b(FamilyBlankActivity familyBlankActivity) {
        this.a = familyBlankActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            this.a.startActivity(new Intent(this.a.getActivity(), FamilyCreateActivity.class));
            this.a.finish();
            return;
        }
        Builder cVar = new c(this, R.style.SimpleDialog_Fullscreen);
        cVar.title(this.a.getString(R.string.family_warm_prompt)).message(baseResponse.getSimpleDataStr("err_msg")).positiveAction(this.a.getString(R.string.family_create_i_know)).setCancelable(false);
        AppUtils.showDialogFragment(this.a.getActivity(), cVar);
    }

    public void onFailed(int i, String str) {
        Builder dVar = new d(this, R.style.SimpleDialog_Fullscreen);
        dVar.title(this.a.getString(R.string.family_warm_prompt)).message(str).positiveAction(this.a.getString(R.string.family_create_i_know)).setCancelable(false);
        AppUtils.showDialogFragment(this.a.getActivity(), dVar);
    }
}
