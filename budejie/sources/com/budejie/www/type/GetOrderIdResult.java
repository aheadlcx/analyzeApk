package com.budejie.www.type;

public class GetOrderIdResult {
    private String channel;
    private String order_id;
    private String order_status;
    private String status;
    private String total_free;

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(String str) {
        this.order_id = str;
    }

    public String getOrder_status() {
        return this.order_status;
    }

    public void setOrder_status(String str) {
        this.order_status = str;
    }

    public String getTotal_free() {
        return this.total_free;
    }

    public void setTotal_free(String str) {
        this.total_free = str;
    }
}
