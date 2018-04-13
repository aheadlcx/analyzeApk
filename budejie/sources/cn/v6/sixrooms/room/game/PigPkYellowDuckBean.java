package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.bean.MessageBean;
import java.util.List;

public class PigPkYellowDuckBean extends MessageBean {
    private static final long serialVersionUID = 9118129592272569844L;
    private String etm;
    private List<PigPkYellowDuckUser> prop;
    private String state;
    private String stm;
    private String title;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getEtm() {
        return this.etm;
    }

    public void setEtm(String str) {
        this.etm = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getStm() {
        return this.stm;
    }

    public void setStm(String str) {
        this.stm = str;
    }

    public List<PigPkYellowDuckUser> getProp() {
        return this.prop;
    }

    public void setProp(List<PigPkYellowDuckUser> list) {
        this.prop = list;
    }
}
