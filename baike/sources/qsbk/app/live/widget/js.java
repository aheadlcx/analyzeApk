package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.live.ui.helper.InspectHelper;

class js implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    js(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public void onClick(View view) {
        if (PreferenceUtils.instance().getString("inspect_mode", "inspect_off").equals("inspect_on")) {
            InspectHelper.buildInspectMenu(this.b.a, this.a, this.b.v);
        } else if (this.b.z != null) {
            this.b.z.doReport(this.a);
        }
    }
}
