package qsbk.app.live.model;

public class LiveCommentMsg extends LiveMessageBase {
    public LiveCommentMsgContent m;

    public LiveCommentMsg(long j, int i, String str) {
        super(j, i);
        this.m = new LiveCommentMsgContent();
        this.m.c = str;
    }
}
