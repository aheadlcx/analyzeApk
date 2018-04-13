package qsbk.app.live.widget;

import java.util.Comparator;
import qsbk.app.live.model.GameRole;

class cj implements Comparator<GameRole> {
    final /* synthetic */ GameView a;

    cj(GameView gameView) {
        this.a = gameView;
    }

    public int compare(GameRole gameRole, GameRole gameRole2) {
        if (gameRole.getRoleId() < gameRole2.getRoleId()) {
            return -1;
        }
        return gameRole.getRoleId() == gameRole2.getRoleId() ? 0 : 1;
    }
}
