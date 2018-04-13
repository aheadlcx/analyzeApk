package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.room.bean.OnHeadlineBean;
import java.util.List;

public class InitHeadLineBean extends MessageBean {
    private List<OnHeadlineBean> content;

    public List<OnHeadlineBean> getContent() {
        return this.content;
    }

    public void setContent(List<OnHeadlineBean> list) {
        this.content = list;
    }
}
