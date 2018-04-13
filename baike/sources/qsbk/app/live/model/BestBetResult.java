package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import qsbk.app.core.model.User;

public class BestBetResult {
    public String a;
    public int l;
    public String n;
    public int p;
    public int r;
    public long s;
    public String t;
    public long u;
    public long w;

    @JsonIgnore
    public int getRank() {
        return this.r;
    }

    @JsonIgnore
    public User getUser() {
        User user = new User();
        user.id = this.u;
        user.name = getName();
        user.headurl = getAvatar();
        user.origin = this.s;
        return user;
    }

    @JsonIgnore
    public String getName() {
        return this.n;
    }

    @JsonIgnore
    public String getAvatar() {
        return this.a;
    }

    @JsonIgnore
    public String getAvatarTemplate() {
        return this.t;
    }

    @JsonIgnore
    public void setAvatar(String str) {
        this.a = str;
    }

    @JsonIgnore
    public long getWinNum() {
        return this.w;
    }
}
