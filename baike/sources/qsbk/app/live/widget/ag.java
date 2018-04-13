package qsbk.app.live.widget;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class ag implements OnClickListener {
    final /* synthetic */ FamilyGatherDialog a;

    ag(FamilyGatherDialog familyGatherDialog) {
        this.a = familyGatherDialog;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.f.getText().toString())) {
            ToastUtil.Short(R.string.family_bugle_not_empty);
        } else if (this.a.f.getText().toString().length() < 2) {
            ToastUtil.Short(R.string.family_bugle_too_short);
        } else {
            NetRequest.getInstance().post(UrlConstants.FAMILY_BUGLE, new ah(this));
        }
    }
}
