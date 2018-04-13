package cn.v6.sixrooms.pay.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {
    private static final long serialVersionUID = 1;
    private AlipaylessMsg alipaylessMsg;
    private String key;
    private String msg;
    private String orderid;
    private String price;

    public AlipaylessMsg getAlipaylessMsg() {
        return this.alipaylessMsg;
    }

    public void setAlipaylessMsg(AlipaylessMsg alipaylessMsg) {
        this.alipaylessMsg = alipaylessMsg;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String str) {
        this.orderid = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String toString() {
        return "OrderBean [orderid=" + this.orderid + ", msg=" + this.msg + ", key=" + this.key + "]";
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }
}
