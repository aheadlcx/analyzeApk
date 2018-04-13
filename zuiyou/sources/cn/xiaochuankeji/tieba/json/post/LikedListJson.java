package cn.xiaochuankeji.tieba.json.post;

import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class LikedListJson {
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public long offset;
    @JSONField(name = "list")
    public List<PostDataBean> postDataBeanList;
}
