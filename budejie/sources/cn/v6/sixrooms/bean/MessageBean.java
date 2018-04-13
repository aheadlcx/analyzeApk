package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.hall.BaseBean;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageBean extends BaseBean {
    private static final long serialVersionUID = 1;
    protected long tm;
    private int typeId;

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int i) {
        this.typeId = i;
    }

    public String getTm() {
        return getTime(this.tm);
    }

    public void setTm(long j) {
        this.tm = j;
    }

    public String getTime(long j) {
        return new SimpleDateFormat("HH:mm").format(new Date(1000 * j));
    }

    public String toString() {
        return "MessageBean [typeId=" + this.typeId + ", tm=" + this.tm + "]";
    }
}
