package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.utils.DialogUtils;

final class ba implements OnClickListener {
    final /* synthetic */ HistoryActivity a;

    ba(HistoryActivity historyActivity) {
        this.a = historyActivity;
    }

    public final void onClick(View view) {
        if (this.a.e == null) {
            this.a.e = new DialogUtils(this.a).createConfirmDialog(1, "此操作将清空所有历史记录", new bb(this));
        }
        if (!this.a.e.isShowing()) {
            this.a.e.show();
        }
    }
}
