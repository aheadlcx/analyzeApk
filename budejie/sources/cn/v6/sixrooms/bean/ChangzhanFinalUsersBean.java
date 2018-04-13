package cn.v6.sixrooms.bean;

import java.io.Serializable;
import java.util.List;

public class ChangzhanFinalUsersBean extends MessageBean implements Serializable {
    private static final long serialVersionUID = 1;
    private List<ChangzhanFinalUserBean> content;
    private String flag;

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public List<ChangzhanFinalUserBean> getContent() {
        return this.content;
    }

    public void setContent(List<ChangzhanFinalUserBean> list) {
        this.content = list;
    }
}
