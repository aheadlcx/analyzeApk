package com.budejie.www.type;

import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import java.util.List;

public class TopicList {
    private ListInfo info;
    private ListItemObject top_topic;
    private List<ListItemObject> topics;

    public ListInfo getInfo() {
        return this.info;
    }

    public void setInfo(ListInfo listInfo) {
        this.info = listInfo;
    }

    public List<ListItemObject> getTopics() {
        return this.topics;
    }

    public void setTopics(List<ListItemObject> list) {
        this.topics = list;
    }

    public ListItemObject getTop_topic() {
        return this.top_topic;
    }

    public void setTop_topic(ListItemObject listItemObject) {
        this.top_topic = listItemObject;
    }
}
