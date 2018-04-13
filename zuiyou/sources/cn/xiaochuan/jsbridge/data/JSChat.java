package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSChat {
    public static final String a = "openChatDialog";
    @JSONField(name = "avatar")
    public long avatar;
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "gender")
    public int gender;
    @JSONField(name = "id")
    public long mid;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "official")
    public int official;
}
