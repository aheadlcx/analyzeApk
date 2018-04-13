package qsbk.app.live.widget;

class at implements Runnable {
    final /* synthetic */ FanfanleGameView a;

    at(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void run() {
        this.a.mLiveBaseActivity.sendNextRound(this.a.a.getGameId(), this.a.a.getGameRoundId(), 1);
    }
}
