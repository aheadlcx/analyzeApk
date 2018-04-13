package qsbk.app.fragments;

import android.os.Build.VERSION;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ShowcaseDialog.Builder;

class jy implements OnGlobalLayoutListener {
    final /* synthetic */ QiuyouCircleFragment a;

    jy(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onGlobalLayout() {
        if (VERSION.SDK_INT >= 16) {
            this.a.j.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.a.j.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        int[] iArr = new int[2];
        int dip2px = UIHelper.dip2px(this.a.getActivity(), 48.0f);
        this.a.j.getLocationInWindow(iArr);
        new Builder(this.a.getActivity()).setShowcaseMessage("补签卡来了，赶快来试试").setShowAtXY(UIHelper.dip2px(this.a.getActivity(), 15.0f), dip2px + UIHelper.dip2px(this.a.getActivity(), 12.0f)).build().show();
        SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.SP_CIRCLE_SIGN_TIP, true);
    }
}
