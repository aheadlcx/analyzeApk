package cn.xiaochuankeji.tieba.ui.hollow.report;

import com.alibaba.fastjson.annotation.JSONField;

public class ReportPlayHollowJson {
    @JSONField(name = "dur")
    public long audioDuration;
    @JSONField(name = "uri")
    public String audioUri;
    @JSONField(name = "url")
    public String audioUrl;
    @JSONField(name = "c_type")
    public long cType;
    @JSONField(name = "delay_time")
    public long delayTime;
    @JSONField(name = "dt")
    public int deviceType = 0;
    @JSONField(name = "audio_author_mid")
    public long memberId;
    @JSONField(name = "owner")
    public String owner;
    @JSONField(name = "owner_id")
    public long ownerId;
    @JSONField(name = "play_dur")
    public long playDur;
    @JSONField(name = "success")
    public long success;
    @JSONField(name = "ver")
    public String version;
}
