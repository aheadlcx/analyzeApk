package cn.xiaochuankeji.tieba.background.utils.share;

import cn.xiaochuankeji.tieba.background.utils.c.a;

public class TopicShareStruct extends WebPageShareStruct {
    private String mTopicName;

    public TopicShareStruct(String str, String str2, String str3) {
        this.mTopicName = str;
        this.thumbPath = str2;
        this.targetUrl = str3;
    }

    public String getTitleBy(int i) {
        String str = a.c().e().u;
        if (str.equals("")) {
            return "发现一个有趣的话题";
        }
        return str;
    }

    public String getDescBy(int i) {
        String str = a.c().e().v;
        if (str.equals("")) {
            return this.mTopicName;
        }
        if (str.contains(topicNameArg)) {
            return str.replaceAll(topicNameReplacedArg, this.mTopicName);
        }
        return str;
    }
}
