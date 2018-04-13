package qsbk.app.live.model;

public class LiveDollActionMessage extends LiveMessage {
    private static long a;
    public LiveDollActionMessageContent m;

    public LiveDollActionMessage(long j, int i, int i2) {
        super(j, i);
        this.m = new LiveDollActionMessageContent();
        this.m.t = i2;
        this.m.i = a;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public static void setRoundId(long j) {
        a = j;
    }

    public static long getRoundId() {
        return a;
    }
}
