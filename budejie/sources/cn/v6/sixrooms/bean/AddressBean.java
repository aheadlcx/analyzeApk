package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class AddressBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String address;
    private int port;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }
}
