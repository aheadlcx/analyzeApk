package cn.v6.sixrooms.room;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.engine.RoomRepertoryGiftEngine.CallBack;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import java.util.ArrayList;

final class y implements CallBack {
    final /* synthetic */ RoomActivity a;

    y(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void result(ArrayList<RepertoryBean> arrayList) {
        this.a.D = new UpdateGiftNumBean(arrayList);
        this.a.a(this.a.D);
        this.a.ai = 2;
    }

    public final void error(int i) {
        if (this.a.ai > 0 && LoginUtils.getLoginUserBean() != null) {
            this.a.aj.getRepertory(this.a.A.getId(), LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        }
        this.a.ai = this.a.ai - 1;
    }
}
