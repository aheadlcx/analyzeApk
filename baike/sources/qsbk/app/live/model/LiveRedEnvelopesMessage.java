package qsbk.app.live.model;

public class LiveRedEnvelopesMessage extends LiveMessage {
    public LiveRedEnvelopesMessageContent m;

    public LiveRedEnvelopes getRedEnvelopes() {
        return (this.m == null || this.m.items == null || this.m.items.isEmpty()) ? null : (LiveRedEnvelopes) this.m.items.get(0);
    }

    public int getNum() {
        return (this.m == null || this.m.items == null) ? 0 : this.m.items.size();
    }
}
