package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class OSSTokenAuth {
    @JSONField(name = "appsig")
    public String appSignature;
}
