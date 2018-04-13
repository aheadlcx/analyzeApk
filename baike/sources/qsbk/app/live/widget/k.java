package qsbk.app.live.widget;

import qsbk.app.live.model.GameRole;

class k implements Runnable {
    final /* synthetic */ GameRole a;
    final /* synthetic */ CatAndDogGameView b;

    k(CatAndDogGameView catAndDogGameView, GameRole gameRole) {
        this.b = catAndDogGameView;
        this.a = gameRole;
    }

    public void run() {
        this.b.b(this.a.getRoleId());
    }
}
