package qsbk.app.live.widget;

import android.app.Activity;
import qsbk.app.core.provider.UserInfoProvider;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.ui.LiveBaseActivity;

class dp extends Builder {
    final /* synthetic */ GameWinDialog a;

    dp(GameWinDialog gameWinDialog, int i) {
        this.a = gameWinDialog;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        UserInfoProvider userInfoProvider = AppUtils.getInstance().getUserInfoProvider();
        Activity activity = this.a.c;
        LiveBaseActivity liveBaseActivity = this.a.c;
        userInfoProvider.toPay(activity, 103);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
