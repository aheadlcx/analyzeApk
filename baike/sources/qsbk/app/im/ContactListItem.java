package qsbk.app.im;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContactListItem {
    private static final SimpleDateFormat a = new SimpleDateFormat("yy-MM-dd");
    private static final Calendar b = Calendar.getInstance();
    public long atMsgId;
    public int atType;
    private final Calendar c = Calendar.getInstance();
    private String d = null;
    public String icon;
    public String id;
    public int inout;
    public String lastMsgFromId;
    public String lastMsgFromName;
    public String mLastContent = null;
    public long mLastUpdateTime = 0;
    public int mimeType;
    public long msgId;
    public String name;
    public int status;
    public int type = 0;
    public int unreadCount = 0;

    public String toString() {
        return "ContactListItem{name='" + this.name + '\'' + ", id='" + this.id + '\'' + ", type=" + this.type + ", mLastContent='" + this.mLastContent + '\'' + ", mLastUpdateTime=" + this.mLastUpdateTime + ", msgId=" + this.msgId + ", icon='" + this.icon + '\'' + ", status=" + this.status + ", unreadCount=" + this.unreadCount + ", mimeType=" + this.mimeType + ", inout=" + this.inout + ", atMsgId=" + this.atMsgId + ", lastMsgFromId='" + this.lastMsgFromId + '\'' + ", mLastUpdateCalendar=" + this.c + ", mFormatTimeString='" + this.d + '\'' + '}';
    }

    @SuppressLint({"DefaultLocale"})
    public String getFormatTime() {
        if (this.d != null) {
            return this.d;
        }
        if (this.mLastUpdateTime <= TimeUtils.UTC_START) {
            this.d = "";
            return this.d;
        }
        b.setTimeInMillis(System.currentTimeMillis());
        this.c.setTimeInMillis(this.mLastUpdateTime);
        if (TimeUtils.isSameDay(b, this.c)) {
            this.d = String.format("%s:%02d", new Object[]{Integer.valueOf(this.c.get(11)), Integer.valueOf(this.c.get(12))});
            return this.d;
        } else if (TimeUtils.isYesterDay(b, this.c)) {
            this.d = "昨天";
            return this.d;
        } else if (TimeUtils.inSameWeek(b, this.c)) {
            this.d = TimeUtils.getDayOfWeek(this.c);
            return this.d;
        } else {
            this.d = a.format(new Date(this.c.getTimeInMillis()));
            return this.d;
        }
    }

    public boolean isGroupMsg() {
        return this.type == 3;
    }

    public boolean isUser() {
        return this.type == 0;
    }

    public boolean isOfficial() {
        return this.type == 1;
    }

    public boolean isGroupNotice() {
        return this.type == 6;
    }
}
