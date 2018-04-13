package cn.xiaochuankeji.tieba.background.assessor;

import com.alibaba.fastjson.annotation.JSONField;

public class ReportAssessResultJson {
    @JSONField(name = "correct")
    public int correct;
    @JSONField(name = "reason")
    public String reason;
    @JSONField(name = "recper")
    public int recper;
}
