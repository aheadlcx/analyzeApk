package cn.v6.sixrooms.room.IM;

public class IMBlackListManager extends IMBaseManager {
    private static volatile IMBlackListManager b;
    private int a;

    public static IMBlackListManager getInstance() {
        if (b == null) {
            synchronized (IMBlackListManager.class) {
                if (b == null) {
                    b = new IMBlackListManager();
                }
            }
        }
        return b;
    }

    public int getBlackListNum() {
        return this.a;
    }

    public void setBlackListNum(int i) {
        this.a = i;
    }

    public void add() {
        this.a++;
    }

    public void minus() {
        if (this.a > 0) {
            this.a--;
        }
    }

    public void destroy() {
        b = null;
    }

    public void clearAll() {
        this.a = 0;
    }
}
