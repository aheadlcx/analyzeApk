package cn.xiaochuankeji.tieba.analyse.log;

import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class LogCommandModel {
    @JSONField(name = "cellular")
    public int cellular;
    @JSONField(name = "clientid")
    public String clientId;
    @JSONField(name = "mask")
    public int mask;
    @JSONField(name = "max_size")
    public int maxSize;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "opid")
    public String opid;
    @JSONField(name = "type")
    public int type;
}
