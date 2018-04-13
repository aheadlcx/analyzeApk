package cn.v6.sixrooms.bean;

import java.util.List;

public class SongLiveListBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private List<SubLiveListBean> liveList;
    private String type;

    public List<SubLiveListBean> getLiveList() {
        return this.liveList;
    }

    public void setLiveList(List<SubLiveListBean> list) {
        this.liveList = list;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
