package cn.xiaochuankeji.tieba.json.account;

import com.alibaba.fastjson.annotation.JSONField;

public class AccountCheckJson {
    @JSONField(name = "enable")
    private int enable;
    @JSONField(name = "msg")
    private String msg;

    public int getEnable() {
        return this.enable;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setEnable(int i) {
        this.enable = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public boolean isEnable() {
        return this.enable == 1;
    }
}
