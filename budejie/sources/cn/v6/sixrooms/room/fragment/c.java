package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.listener.OnChatOnlickListener;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;

final class c implements OnChatOnlickListener {
    final /* synthetic */ FullScreenRoomFragment a;

    c(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onPublicChatClickable(UserInfoBean userInfoBean, String str) {
        this.a.setChatClickable(userInfoBean);
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUPROFILE);
    }
}
