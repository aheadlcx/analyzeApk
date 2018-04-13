package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class SubmitUrlJson {
    @JSONField(name = "url_type")
    public int linkType;
    @JSONField(name = "res_id")
    public String resId;
    @JSONField(name = "status")
    public int status;
}
