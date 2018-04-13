package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.engine.FansListEngine.CallBack;
import cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter;
import java.util.List;

final class ag implements CallBack {
    final /* synthetic */ RankPop a;

    ag(RankPop rankPop) {
        this.a = rankPop;
    }

    public final void fansList(List<FansBean> list, List<FansBean> list2, List<FansBean> list3) {
    }

    public final void fansList(List<FansBean> list, List<FansBean> list2) {
        RankPop.a(this.a).clear();
        RankPop.a(this.a).addAll(list);
        if (RankPop.b(this.a)) {
            RankPop.a(this.a, new FansListOfFullScreenAdapter(this.a.mBaseRoomActivity, RankPop.a(this.a)));
            RankPop.d(this.a).setAdapter(RankPop.c(this.a));
            RankPop.e(this.a);
            return;
        }
        RankPop.c(this.a).notifyDataSetChanged();
    }

    public final void error(int i) {
        this.a.mBaseRoomActivity.showErrorToast(i);
    }

    public final void handleErrorInfo(String str, String str2) {
        if (this.a.mBaseRoomActivity != null) {
            this.a.mBaseRoomActivity.handleErrorResult(str, str2, this.a.mBaseRoomActivity);
        }
    }
}
