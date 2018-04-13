package com.budejie.www.activity.phonenumber;

import java.io.Serializable;

public class SimContactInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String contactName;
    private String phoneNumber;

    public SimContactInfo(String str, String str2) {
        this.contactName = str;
        this.phoneNumber = str2;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String str) {
        this.contactName = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }
}
