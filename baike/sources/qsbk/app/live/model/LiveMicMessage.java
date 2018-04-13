package qsbk.app.live.model;

import android.text.TextUtils;
import qsbk.app.core.model.User;

public class LiveMicMessage extends LiveMessage {
    public LiveMicMessageContent m;

    public LiveMicMessage(long j, int i, int i2, String str, long j2, long j3) {
        super(j, i);
        this.m = new LiveMicMessageContent();
        this.m.o = i2;
        if (!TextUtils.isEmpty(str)) {
            this.m.c = str;
        }
        this.m.s = j2;
        this.m.i = j3;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public String getChannel() {
        return this.m != null ? this.m.c : "";
    }

    public int getOperation() {
        return this.m != null ? this.m.o : 0;
    }

    public long getSource() {
        return this.m != null ? this.m.s : 0;
    }

    public long getSourceId() {
        return this.m != null ? this.m.i : 0;
    }

    public User getMicUser() {
        User user = new User();
        if (this.m != null) {
            user.origin_id = this.m.i;
            user.origin = this.m.s;
            user.name = this.m.n;
            user.headurl = this.m.a;
            user.level = this.m.l;
        }
        return user;
    }

    public boolean isMicMsgFromMe() {
        return this.m != null && this.m.f;
    }
}
