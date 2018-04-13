package qsbk.app.live.widget;

import java.util.Comparator;

class bo implements Comparator<String> {
    final /* synthetic */ GameBetButton a;

    bo(GameBetButton gameBetButton) {
        this.a = gameBetButton;
    }

    public int compare(String str, String str2) {
        return Long.valueOf(str).compareTo(Long.valueOf(str2));
    }
}
