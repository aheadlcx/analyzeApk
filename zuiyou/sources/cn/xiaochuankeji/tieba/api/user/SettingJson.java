package cn.xiaochuankeji.tieba.api.user;

import com.alibaba.fastjson.annotation.JSONField;

public class SettingJson {
    @JSONField(name = "good")
    public int good;
    @JSONField(name = "indirect")
    public int indirect;
    @JSONField(name = "msg")
    public int msg;
    @JSONField(name = "review")
    public int review;

    public String toString() {
        return "SettingJson{good=" + this.good + ", review=" + this.review + ", msg=" + this.msg + ", indirect=" + this.indirect + '}';
    }
}
