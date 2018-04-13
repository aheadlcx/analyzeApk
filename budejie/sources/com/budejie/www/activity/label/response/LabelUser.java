package com.budejie.www.activity.label.response;

import com.budejie.www.type.SearchItem;
import java.io.Serializable;
import java.util.List;

public class LabelUser implements Serializable {
    private String forum_rule;
    private LabelUser$InfoBean info;
    private ListBean list;

    public static class ListBean {
        private List<SearchItem> manito;
        private List<SearchItem> master;

        public List<SearchItem> getManito() {
            return this.manito;
        }

        public void setManito(List<SearchItem> list) {
            this.manito = list;
        }

        public List<SearchItem> getMaster() {
            return this.master;
        }

        public void setMaster(List<SearchItem> list) {
            this.master = list;
        }
    }

    public LabelUser$InfoBean getInfo() {
        return this.info;
    }

    public void setInfo(LabelUser$InfoBean labelUser$InfoBean) {
        this.info = labelUser$InfoBean;
    }

    public ListBean getList() {
        return this.list;
    }

    public void setList(ListBean listBean) {
        this.list = listBean;
    }

    public String getForum_rule() {
        return this.forum_rule;
    }

    public void setForum_rule(String str) {
        this.forum_rule = str;
    }
}
