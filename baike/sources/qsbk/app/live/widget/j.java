package qsbk.app.live.widget;

import qsbk.app.live.model.GameRole;

class j implements Runnable {
    final /* synthetic */ GameRole a;
    final /* synthetic */ CatAndDogGameView b;

    j(CatAndDogGameView catAndDogGameView, GameRole gameRole) {
        this.b = catAndDogGameView;
        this.a = gameRole;
    }

    public void run() {
        GameBetView roleBetView = this.b.getRoleBetView(this.a.getRoleId());
        if (roleBetView != null) {
            roleBetView.loadData(this.a);
        }
        PokerGroup a = this.b.c(this.a.getRoleId());
        if (a != null) {
            a.loadPokers(this.a.getGameResult());
        }
    }
}
