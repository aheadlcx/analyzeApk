package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.UploadHeadPortraitEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SendBroadcastUtils;
import cn.v6.sixrooms.utils.ToastUtils;

final class at implements CallBack {
    final /* synthetic */ MineFragment a;

    at(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void resultInfo(String str) {
        GlobleValue.getUserBean().setPicuser(str);
        MineFragment.t(this.a).setPicuser(str);
        this.a.updateUserHeadImage();
        SendBroadcastUtils.sendUserPicBroadcast(MineFragment.p(this.a));
        ToastUtils.showToast(this.a.getString(R.string.headUploadSuccessful));
        MineFragment.j(this.a);
    }

    public final void error(int i) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).showErrorToast(i);
    }

    public final void errorString(String str, String str2) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).handleErrorResult(str, str2, MineFragment.f(this.a));
    }
}
