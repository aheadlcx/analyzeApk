package qsbk.app.share.message;

import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.PagerSlidingTabStrip.ITabOnClickListener;

class b implements ITabOnClickListener {
    final /* synthetic */ ChooseQiuyouFragment a;

    b(ChooseQiuyouFragment chooseQiuyouFragment) {
        this.a = chooseQiuyouFragment;
    }

    public void onTabClickListener(int i) {
        DebugUtil.debug(ChooseQiuyouFragment.a, "onTabClickListener");
    }
}
