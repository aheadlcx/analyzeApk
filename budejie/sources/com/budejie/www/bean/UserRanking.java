package com.budejie.www.bean;

import java.io.Serializable;
import java.util.List;

public class UserRanking implements Serializable {
    List<LabelUser> recomList;
    List<LabelUser> topList;

    public List<LabelUser> getRecomList() {
        return this.recomList;
    }

    public void setRecomList(List<LabelUser> list) {
        this.recomList = list;
    }

    public List<LabelUser> getTopList() {
        return this.topList;
    }

    public void setTopList(List<LabelUser> list) {
        this.topList = list;
    }
}
