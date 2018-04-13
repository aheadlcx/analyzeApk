package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.room.dialog.HeadLineDialog;
import cn.v6.sixrooms.room.dialog.MoreDialog.MoreItemClickListener;
import cn.v6.sixrooms.room.dialog.SongDialog;
import cn.v6.sixrooms.room.statistic.StatiscProxy;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;

final class aa implements MoreItemClickListener {
    final /* synthetic */ FullScreenRoomFragment a;

    aa(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void chooseSong() {
        if (FullScreenRoomFragment.p(this.a) != null) {
            if (FullScreenRoomFragment.e(this.a) == null) {
                FullScreenRoomFragment.a(this.a, new SongDialog(FullScreenRoomFragment.f(this.a)));
                FullScreenRoomFragment.e(this.a).setTitle(FullScreenRoomFragment.a(Integer.valueOf(FullScreenRoomFragment.p(this.a).getRoominfoBean().getWealthrank()).intValue()));
                FullScreenRoomFragment.e(this.a).setLayout(this.a.mRoomType);
                FullScreenRoomFragment.e(this.a).setOnSongOnClick(FullScreenRoomFragment.D(this.a));
                FullScreenRoomFragment.e(this.a).setOnDismissListener(new ab(this));
            }
            FullScreenRoomFragment.e(this.a).show();
            FullScreenRoomFragment.E(this.a);
            FullScreenRoomFragment.c(this.a, true);
            if (FullScreenRoomFragment.f(this.a) != null) {
                String id = FullScreenRoomFragment.p(this.a).getRoominfoBean().getId();
                if (id != null) {
                    FullScreenRoomFragment.f(this.a).sendSongListMessage(id);
                }
            }
            StatiscProxy.roomSongClick();
        }
    }

    public final void headLine() {
        if (FullScreenRoomFragment.s(this.a) != null) {
            if (FullScreenRoomFragment.t(this.a) == null) {
                FullScreenRoomFragment.a(this.a, new HeadLineDialog(FullScreenRoomFragment.f(this.a), FullScreenRoomFragment.p(this.a)));
                FullScreenRoomFragment.t(this.a).setLayout(this.a.mRoomType);
                FullScreenRoomFragment.t(this.a).setOnDismissListener(new ac(this));
            }
            if (!(this.a.getActivity() == null || this.a.getActivity().isFinishing() || FullScreenRoomFragment.t(this.a).isShowing())) {
                FullScreenRoomFragment.t(this.a).show();
            }
            FullScreenRoomFragment.E(this.a);
            FullScreenRoomFragment.c(this.a, true);
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_HEADLINE);
        }
    }

    public final void definition() {
        FullScreenRoomFragment.F(this.a).changeDefinition();
    }

    public final void fullScreen() {
        if (this.a.mRoomType == 0) {
            this.a.mRoomType = 2;
            FullScreenRoomFragment.f(this.a).requestType(2);
        }
    }
}
