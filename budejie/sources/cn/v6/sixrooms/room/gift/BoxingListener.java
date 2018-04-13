package cn.v6.sixrooms.room.gift;

public interface BoxingListener {
    void handleBoxingClose(BoxingCloseBean boxingCloseBean);

    void handleBoxingFlushVotes(BoxingBoxerVotes boxingBoxerVotes);

    void handleBoxingInitialization(BoxingBean boxingBean);

    void handleBoxingStart(BoxingBean boxingBean);

    void handleBoxingVote(BoxingVoteBean boxingVoteBean);

    void handleBoxingWinVictory(BoxingWinVictoryBean boxingWinVictoryBean);

    void handleBoxingWinning(BoxingWinningBean boxingWinningBean);
}
