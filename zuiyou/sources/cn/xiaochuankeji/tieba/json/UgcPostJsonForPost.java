package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.background.data.post.Moment;
import com.alibaba.fastjson.annotation.JSONField;

public class UgcPostJsonForPost {
    @JSONField(name = "post")
    public Moment post;
}
