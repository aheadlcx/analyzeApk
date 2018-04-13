package cn.v6.sixrooms.pay.bean;

import java.io.Serializable;
import java.text.NumberFormat;

public class PaySelectBean implements PayInfoInterface, Serializable {
    private static final long serialVersionUID = 1;
    private String coin6;
    private String content;
    private int id;
    private String money;
    private String msg;
    private int type;

    public PaySelectBean(int i, int i2, String str) {
        this.type = i;
        this.id = i2;
        this.content = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getContent() {
        if (this.type == 1) {
            return "充值" + this.money + "元兑换" + this.coin6 + "六币";
        }
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getFormatMoney() {
        NumberFormat instance = NumberFormat.getInstance();
        instance.setMinimumFractionDigits(2);
        instance.setMaximumIntegerDigits(64);
        return instance.format(Long.parseLong(this.money)) + "元";
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String str) {
        this.money = str;
    }

    public String getCoin6() {
        return this.coin6;
    }

    public void setCoin6(String str) {
        this.coin6 = str;
    }

    public String toString() {
        return "PaySelectBean [type=" + this.type + ", id=" + this.id + ", content=" + this.content + ", money=" + this.money + ", coin6=" + this.coin6 + ", msg=" + this.msg + "]";
    }
}
