package qsbk.app.live.widget;

import qsbk.app.live.model.GameRole;

class fd implements Runnable {
    final /* synthetic */ GameRole a;
    final /* synthetic */ HLNBGameView b;

    fd(HLNBGameView hLNBGameView, GameRole gameRole) {
        this.b = hLNBGameView;
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
