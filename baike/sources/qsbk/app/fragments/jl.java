package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.ShowcaseDialog.Builder;

class jl extends BroadcastReceiver {
    final /* synthetic */ QiuyouCircleFragment a;

    jl(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (!SharePreferenceUtils.getSharePreferencesBoolValue(Constants.ACTION_CIRCLE_LIKE) && QsbkApp.currentUser != null) {
            int[] iArr = new int[2];
            if (this.a.u != null) {
                this.a.u.getLocationInWindow(iArr);
                int i = iArr[0];
                this.a.u.getLocationOnScreen(iArr);
                iArr[0] = i;
                iArr[1] = iArr[1] + Util.statusBarHeight;
            }
            new Builder(this.a.getActivity()).setShowAtXY(UIHelper.dip2px(this.a.getActivity(), 8.0f), UIHelper.dip2px(this.a.getActivity(), 48.0f) + UIHelper.dip2px(this.a.getActivity(), 12.0f)).setGravity(53).setTextBackGroundResource(R.drawable.bg_showcase_right).setShowcaseMessage(QsbkApp.currentUser.isNewUser() ? "走过路过，不如发个动态" : "糗友圈可以发视频咯～").build().show();
            SharePreferenceUtils.setSharePreferencesValue(Constants.ACTION_CIRCLE_LIKE, true);
        }
    }
}
