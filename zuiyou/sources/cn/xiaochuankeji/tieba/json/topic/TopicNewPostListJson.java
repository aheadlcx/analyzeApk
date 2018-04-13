package cn.xiaochuankeji.tieba.json.topic;

import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TopicNewPostListJson {
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "next_list_cb")
    public String nextCb;
    @JSONField(name = "list")
    public List<PostDataBean> postList;
    @JSONField(name = "t")
    public int time;
}
