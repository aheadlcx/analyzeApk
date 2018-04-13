package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.UnReadFeedback;
import cn.tatagou.sdk.util.p;

class MineFragment$3 extends a<CommPojo<UnReadFeedback>> {
    final /* synthetic */ MineFragment a;

    MineFragment$3(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public void onApiDataResult(CommPojo<UnReadFeedback> commPojo, int i) {
        super.onApiDataResult(commPojo, i);
        if (this.a.isAdded() && commPojo != null && commPojo.getData() != null) {
            UnReadFeedback unReadFeedback = (UnReadFeedback) commPojo.getData();
            int parseInt = p.isEmpty(unReadFeedback.getUnRead()) ? 0 : Integer.parseInt(unReadFeedback.getUnRead());
            if (parseInt > 0) {
                MineFragment.d(this.a).setBadgeCount(parseInt);
                MineFragment.d(this.a).show();
            } else if (parseInt == 0 && MineFragment.d(this.a) != null && MineFragment.d(this.a).isShown()) {
                MineFragment.d(this.a).hide();
            }
        }
    }
}
