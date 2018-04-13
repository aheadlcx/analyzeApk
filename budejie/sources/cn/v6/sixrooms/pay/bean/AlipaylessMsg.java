package cn.v6.sixrooms.pay.bean;

public class AlipaylessMsg {
    private String body;
    private String notify_url;
    private String out_trade_no;
    private String partner;
    private String seller;
    private String sign;
    private String sign_type;
    private String subject;
    private String total_fee;

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getSign_type() {
        return this.sign_type;
    }

    public void setSign_type(String str) {
        this.sign_type = str;
    }

    public String getNotify_url() {
        return this.notify_url;
    }

    public void setNotify_url(String str) {
        this.notify_url = str;
    }

    public String getPartner() {
        return this.partner;
    }

    public void setPartner(String str) {
        this.partner = str;
    }

    public String getSeller() {
        return this.seller;
    }

    public void setSeller(String str) {
        this.seller = str;
    }

    public String getOut_trade_no() {
        return this.out_trade_no;
    }

    public void setOut_trade_no(String str) {
        this.out_trade_no = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public String getTotal_fee() {
        return this.total_fee;
    }

    public void setTotal_fee(String str) {
        this.total_fee = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String toString() {
        return "AlipaylessMsg [partner=" + this.partner + ", seller=" + this.seller + ", out_trade_no=" + this.out_trade_no + ", subject=" + this.subject + ", total_fee=" + this.total_fee + ", sign=" + this.sign + ", notify_url=" + this.notify_url + ", sign_type=" + this.sign_type + ", body=" + this.body + "]";
    }
}
