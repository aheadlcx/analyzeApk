package cn.v6.sixrooms.room.fragment;

import android.content.Context;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.view.runnable.IChooseGiftsListener;
import java.util.ArrayList;

final class b implements IChooseGiftsListener {
    final /* synthetic */ FullScreenRoomFragment a;

    b(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final WrapRoomInfo obtainWrapRoomInfo() {
        return FullScreenRoomFragment.p(this.a);
    }

    public final void onDismiss() {
        FullScreenRoomFragment.H(this.a);
        FullScreenRoomFragment.C(this.a);
        this.a.notifyPublicChatAdapter();
        if (this.a.mPublicChatPage != null) {
            this.a.mPublicChatPage.setSelection();
        }
    }

    public final ArrayList<RepertoryBean> getStockList() {
        return FullScreenRoomFragment.I(this.a);
    }

    public final void gotoLogin() {
        this.a.showLoginDialog();
    }

    public final void publicSend(String str, String str2, String str3, int i, int i2, String str4) {
        ((BaseRoomActivity) getActivity()).sendGift(str, str2, str3, i, i2, str4);
    }

    public final void anonymousSend(String str, String str2, String str3, int i, int i2, String str4) {
        ((BaseRoomActivity) getActivity()).sendAnonymGift(str, str2, str3, i, i2, str4);
    }

    public final void gotoRecharge() {
        StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FGIFT);
        FullScreenRoomFragment.f(this.a).toRechargeActivity();
    }

    public final Context getActivity() {
        return FullScreenRoomFragment.f(this.a);
    }
}
