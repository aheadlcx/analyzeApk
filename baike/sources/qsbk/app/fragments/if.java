package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.ShowcaseDialog.Builder;

class if extends BroadcastReceiver {
    final /* synthetic */ QiushiListFragment a;

    if(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("count", 0);
        Log.i("vote broadcast", "broadcast count = " + intExtra);
        if (QsbkApp.currentUser != null && QsbkApp.currentUser.isNewUser()) {
            if (intExtra == 1) {
                if (!SharePreferenceUtils.getSharePreferencesBoolValue("new_share_showcase" + QsbkApp.currentUser.userId)) {
                    View view = this.a.getView();
                    if (view != null) {
                        view = view.findViewById(R.id.collection_icon);
                        int[] iArr = new int[2];
                        view.getLocationOnScreen(iArr);
                        iArr[1] = (view.getMeasuredHeight() / 2) + iArr[1];
                        new Builder(this.a.getActivity()).setShowAtXY(iArr[0], iArr[1]).setGravity(51).setTextBackGroundResource(R.drawable.bg_showcase_right).setShowcaseMessage("分享给好友乐一乐～").build().show();
                    }
                    SharePreferenceUtils.setSharePreferencesValue("new_share_showcase" + QsbkApp.currentUser.userId, true);
                }
            } else if (intExtra > 2 && !SharePreferenceUtils.getSharePreferencesBoolValue("new_post_showcase" + QsbkApp.currentUser.userId)) {
                int[] iArr2 = new int[2];
                if (this.a.y != null) {
                    this.a.y.getLocationInWindow(iArr2);
                    int i = iArr2[0];
                    this.a.x.getLocationOnScreen(iArr2);
                    iArr2[0] = i;
                    iArr2[1] = iArr2[1] + Util.statusBarHeight;
                }
                new Builder(this.a.getActivity()).setShowAtXY(UIHelper.dip2px(this.a.getActivity(), 15.0f), UIHelper.dip2px(this.a.getActivity(), 48.0f) + UIHelper.dip2px(this.a.getActivity(), 12.0f)).setGravity(53).setTextBackGroundResource(R.drawable.bg_showcase_right).setShowcaseMessage("有糗事，来一发？").build().show();
                SharePreferenceUtils.setSharePreferencesValue("new_post_showcase" + QsbkApp.currentUser.userId, true);
            }
        }
    }
}
