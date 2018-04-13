package com.spriteapp.booklibrary.model.response;

public class PayResponse {
    private float amount;
    private String order_no;
    private PayResponse$PayParams params;
    private String pay_str;
    private String return_code;
    private String transaction_id;

    public String getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(String str) {
        this.transaction_id = str;
    }

    public String getPay_str() {
        return this.pay_str;
    }

    public void setPay_str(String str) {
        this.pay_str = str;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public String getReturn_code() {
        return this.return_code;
    }

    public void setReturn_code(String str) {
        this.return_code = str;
    }

    public String getOrder_no() {
        return this.order_no;
    }

    public void setOrder_no(String str) {
        this.order_no = str;
    }

    public PayResponse$PayParams getParams() {
        return this.params;
    }

    public void setParams(PayResponse$PayParams payResponse$PayParams) {
        this.params = payResponse$PayParams;
    }
}
