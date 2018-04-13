package qsbk.app.live.model;

import java.util.List;

public class LiveGameHistoryData {
    public String CreateAt;
    public int IsWin;
    public String VotePlayers;
    public String e;
    public List<Integer> h;
    public int w;

    public boolean isShunZi() {
        return "s".equals(this.e);
    }
}
