package cn.xiaochuankeji.tieba.json.upload;

import com.alibaba.fastjson.annotation.JSONField;

public class AllCheckJson {
    @JSONField(name = "md5")
    public String md5;
    @JSONField(name = "uri")
    public String uri;
}
