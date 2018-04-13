package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class UgcVideoPublishedDanmakuJson {
    @JSONField(name = "danmaku")
    public UgcVideoDanmakuJson danmakuJson;
}
