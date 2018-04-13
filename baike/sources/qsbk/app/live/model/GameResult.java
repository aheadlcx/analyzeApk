package qsbk.app.live.model;

import java.util.List;

public class GameResult {
    public static final int UNKNOW_RESULT = -1;
    public int c = -1;
    public String e;
    public List<Integer> p;
    public long r;

    public int getResult() {
        return this.c;
    }

    public boolean isShunZi() {
        return "s".equals(this.e);
    }

    public List<Integer> getResultGroup() {
        return this.p;
    }
}
