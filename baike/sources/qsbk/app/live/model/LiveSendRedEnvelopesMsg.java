package qsbk.app.live.model;

import qsbk.app.core.model.RedEnvelopes;

public class LiveSendRedEnvelopesMsg extends LiveMessageBase {
    public LiveSendRedEnvelopesMsgContent m;

    public LiveSendRedEnvelopesMsg(long j, int i, String str, RedEnvelopes redEnvelopes) {
        super(j, i);
        this.m = new LiveSendRedEnvelopesMsgContent();
        this.m.p = str;
        this.m.ii = redEnvelopes.id;
        this.m.c = redEnvelopes.coin;
        this.m.s = redEnvelopes.size;
    }
}
