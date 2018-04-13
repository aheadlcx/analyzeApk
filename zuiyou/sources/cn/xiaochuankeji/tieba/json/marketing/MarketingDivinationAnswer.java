package cn.xiaochuankeji.tieba.json.marketing;

import com.alibaba.fastjson.annotation.JSONField;

public class MarketingDivinationAnswer {
    @JSONField(name = "abtestingid")
    public String abtestingId;
    @JSONField(name = "text")
    public String answerText;
    @JSONField(name = "guide")
    public String shareText;
}
