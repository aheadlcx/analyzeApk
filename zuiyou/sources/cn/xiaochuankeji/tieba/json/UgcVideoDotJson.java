package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class UgcVideoDotJson {
    @JSONField(name = "att_dot")
    public int attDot;
    @JSONField(name = "index_dot")
    public int recommDot;
}
