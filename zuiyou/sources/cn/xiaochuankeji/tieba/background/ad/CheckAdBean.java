package cn.xiaochuankeji.tieba.background.ad;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckAdBean {
    @JSONField(name = "msg")
    public String msg;
    @JSONField(name = "status")
    public int status;
}
