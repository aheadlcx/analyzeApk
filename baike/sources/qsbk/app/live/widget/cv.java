package qsbk.app.live.widget;

import java.util.Comparator;
import qsbk.app.live.model.GameRole;

class cv implements Comparator<GameRole> {
    final /* synthetic */ GameView a;

    cv(GameView gameView) {
        this.a = gameView;
    }

    public int compare(GameRole gameRole, GameRole gameRole2) {
        long j = gameRole.getGameResult().r;
        long j2 = gameRole2.getGameResult().r;
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }
}
