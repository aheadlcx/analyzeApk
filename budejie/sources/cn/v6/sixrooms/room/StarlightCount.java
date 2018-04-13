package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.MessageBean;
import java.io.Serializable;

public class StarlightCount extends MessageBean implements Serializable {
    protected String star;
    protected String uid;

    public String getStar() {
        return this.star;
    }

    public void setStar(String str) {
        this.star = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }
}
