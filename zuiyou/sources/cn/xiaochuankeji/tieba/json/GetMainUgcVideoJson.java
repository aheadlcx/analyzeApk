package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class GetMainUgcVideoJson {
    @JSONField(name = "post")
    public UgcVideoInfoBean mainUgcVideoInfo;
}
