package cn.v6.sixrooms.bean;

import java.util.List;

public class GiftListBean extends MessageBean {
    private static final long serialVersionUID = 9118129592272569844L;
    private List<GiftListItemBean> content;

    public List<GiftListItemBean> getContent() {
        return this.content;
    }

    public void setContent(List<GiftListItemBean> list) {
        this.content = list;
    }
}
