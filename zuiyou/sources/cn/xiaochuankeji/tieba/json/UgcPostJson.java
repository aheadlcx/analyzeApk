package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class UgcPostJson {
    @JSONField(name = "grade")
    public boolean gradle;
    @JSONField(name = "grade_id")
    public long gradleId;
    @JSONField(name = "review")
    public UgcVideoInfoBean reviewVideoInfoBean;
    @JSONField(name = "post")
    public UgcVideoInfoBean ugcVideoInfoBean;
}
