package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveAdmin;

class a implements OnClickListener {
    final /* synthetic */ LiveAdmin a;
    final /* synthetic */ AdminAdapter b;

    a(AdminAdapter adminAdapter, LiveAdmin liveAdmin) {
        this.b = adminAdapter;
        this.a = liveAdmin;
    }

    public void onClick(View view) {
        Builder bVar = new b(this, R.style.SimpleDialog);
        bVar.message(this.b.b.getString(R.string.admin_cancel_hint)).positiveAction(this.b.b.getString(R.string.setting_confirm)).negativeAction(this.b.b.getString(R.string.setting_cancel));
        AppUtils.showDialogFragment((BaseActivity) this.b.b, bVar);
    }
}
