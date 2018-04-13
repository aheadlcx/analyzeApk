package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class UgcVideoMsgGetReviewOne {
    @JSONField(name = "post")
    public UgcVideoInfoBean post;
    @JSONField(name = "review")
    public UgcVideoInfoBean review;
}
