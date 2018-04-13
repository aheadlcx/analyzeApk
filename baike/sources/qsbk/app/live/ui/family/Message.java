package qsbk.app.live.ui.family;

import java.io.Serializable;
import java.util.List;
import qsbk.app.core.model.User;

public class Message implements Serializable {
    public static final int STATUS_AGREED = 1;
    public static final int STATUS_APPLIED = 6;
    public static final int STATUS_DENIED = 2;
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_IGNORED = 3;
    public static final int STATUS_OTHER = -1;
    public String cont;
    public long family_id;
    public List<User> from;
    public String icon;
    public String reply;
    public int status;
    public long time;
    public String type;
    public int unread;

    public User getUser() {
        User user = (this.from == null || this.from.size() <= 0) ? null : (User) this.from.get(0);
        if (user != null) {
            user.headurl = user.avatar;
        }
        return user;
    }

    public String getUserName() {
        User user = getUser();
        if (user != null) {
            return user.name;
        }
        return null;
    }

    public int getUserLevel() {
        User user = getUser();
        if (user != null) {
            return user.level;
        }
        return 0;
    }

    public String getUserBadge() {
        User user = getUser();
        if (user != null) {
            return user.badge;
        }
        return "";
    }

    public String getUserAvatar() {
        User user = getUser();
        if (user != null) {
            return user.headurl;
        }
        return null;
    }

    public int getStatus() {
        return this.status;
    }

    public long getFamilyId() {
        return this.family_id;
    }

    public int getUserFamilyLevel() {
        User user = getUser();
        if (user != null) {
            return user.family_level;
        }
        return 0;
    }
}
