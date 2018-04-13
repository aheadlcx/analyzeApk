package qsbk.app.live.widget;

class as implements Runnable {
    final /* synthetic */ FanfanleGameView a;

    as(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void run() {
        this.a.mLiveBaseActivity.sendNextRound(this.a.a.getGameId(), this.a.a.getGameRoundId(), 0);
    }
}
