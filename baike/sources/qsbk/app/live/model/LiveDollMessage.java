package qsbk.app.live.model;

import qsbk.app.core.model.User;

public class LiveDollMessage extends LiveMessage {
    public static int mBackDiamondNum = 0;
    public LiveDollMessageContent m;

    public LiveDollMessage(long j, int i, int i2) {
        super(j, i);
        this.m = new LiveDollMessageContent();
        this.m.o = i2;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int getOperation() {
        return this.m != null ? this.m.o : 0;
    }

    public String getChannel() {
        return this.m != null ? this.m.c : null;
    }

    public int getTimeLimit() {
        return this.m != null ? this.m.l : 0;
    }

    public long getRoundId() {
        return this.m != null ? this.m.i : 0;
    }

    public int getResult() {
        return this.m != null ? this.m.r : 0;
    }

    public long getSource() {
        return this.m != null ? this.m.s : 0;
    }

    public long getSourceId() {
        return this.m != null ? this.m.d : 0;
    }

    public User getMicUser() {
        User user = new User();
        if (this.m != null) {
            user.origin_id = this.m.d;
            user.origin = this.m.s;
            user.name = this.m.n;
            user.headurl = this.m.a;
        }
        return user;
    }

    public int getPrice() {
        return this.m != null ? this.m.p : 0;
    }

    public int getBackDiamondNum() {
        return this.m != null ? this.m.b : 0;
    }

    public int getFrequency() {
        return this.m != null ? this.m.g : 0;
    }

    public static void setBackDiamondNum(int i) {
        mBackDiamondNum = i;
    }
}
