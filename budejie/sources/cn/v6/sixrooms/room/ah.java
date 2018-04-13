package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.gift.BoxingBean;
import cn.v6.sixrooms.room.gift.BoxingBoxerVotes;
import cn.v6.sixrooms.room.gift.BoxingCloseBean;
import cn.v6.sixrooms.room.gift.BoxingListener;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.room.gift.BoxingWinVictoryBean;
import cn.v6.sixrooms.room.gift.BoxingWinningBean;

final class ah implements BoxingListener {
    final /* synthetic */ RoomActivity a;

    ah(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void handleBoxingInitialization(BoxingBean boxingBean) {
        RoomActivity.a(this.a, boxingBean, 408);
    }

    public final void handleBoxingStart(BoxingBean boxingBean) {
        RoomActivity.a(this.a, boxingBean, 533);
    }

    public final void handleBoxingVote(BoxingVoteBean boxingVoteBean) {
        RoomActivity.a(this.a, boxingVoteBean, 534);
    }

    public final void handleBoxingWinning(BoxingWinningBean boxingWinningBean) {
        RoomActivity.a(this.a, boxingWinningBean, 535);
    }

    public final void handleBoxingFlushVotes(BoxingBoxerVotes boxingBoxerVotes) {
        RoomActivity.a(this.a, boxingBoxerVotes, 536);
    }

    public final void handleBoxingWinVictory(BoxingWinVictoryBean boxingWinVictoryBean) {
        RoomActivity.a(this.a, boxingWinVictoryBean, 537);
    }

    public final void handleBoxingClose(BoxingCloseBean boxingCloseBean) {
        RoomActivity.a(this.a, boxingCloseBean, 538);
    }
}
