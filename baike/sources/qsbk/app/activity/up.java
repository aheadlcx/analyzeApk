package qsbk.app.activity;

import android.view.ViewTreeObserver.OnPreDrawListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.ShowcaseDialog.Builder;

class up implements OnPreDrawListener {
    final /* synthetic */ MyInfoActivity a;

    up(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public boolean onPreDraw() {
        this.a.x.getViewTreeObserver().removeOnPreDrawListener(this);
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("_myinfo_hot_comment_tip")) {
            int[] iArr = new int[2];
            this.a.x.getLocationInWindow(iArr);
            new Builder(this.a).setShowAtXY(iArr[0], iArr[1]).setShowcaseMessage("热评、精选、签到都可以点击哦～").build().show();
            SharePreferenceUtils.setSharePreferencesValue("_myinfo_hot_comment_tip", true);
        }
        return true;
    }
}
