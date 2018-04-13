package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;

class jo implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    jo(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public void onClick(View view) {
        Builder jpVar = new jp(this, R.style.SimpleDialog);
        jpVar.message(this.b.a.getString(this.a.isAdmin() ? R.string.admin_cancel_hint : R.string.admin_set_hint)).positiveAction(this.b.a.getString(R.string.setting_confirm)).negativeAction(this.b.a.getString(R.string.setting_cancel));
        AppUtils.showDialogFragment((BaseActivity) this.b.a, jpVar);
    }
}
