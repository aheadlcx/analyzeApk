package qsbk.app.live.ui.bag;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveBagDataRecord;

class c implements OnClickListener {
    final /* synthetic */ LiveBagDataRecord a;
    final /* synthetic */ BagAdapter b;

    c(BagAdapter bagAdapter, LiveBagDataRecord liveBagDataRecord) {
        this.b = bagAdapter;
        this.a = liveBagDataRecord;
    }

    public void onClick(View view) {
        Builder dVar = new d(this, R.style.SimpleDialog);
        dVar.message("确定要装备该特效吗？").positiveAction(this.b.a.getString(R.string.setting_confirm)).negativeAction(this.b.a.getString(R.string.setting_cancel));
        AppUtils.showDialogFragment((FragmentActivity) this.b.a, dVar);
    }
}
