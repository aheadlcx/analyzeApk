package cn.v6.sixrooms.socket.common;

import java.io.Serializable;

public class CommItem implements Serializable {
    String a = "";
    String b = "";

    public CommItem(String str) {
        initItem(str);
    }

    public String getItemKey() {
        return this.a;
    }

    public void setItemKey(String str) {
        this.a = str;
    }

    public String getItemValue() {
        return this.b;
    }

    public void setItemValue(String str) {
        this.b = str;
    }

    public void initItem(String str) {
        int indexOf = str.indexOf(61);
        if (indexOf > 0) {
            this.a = str.substring(0, indexOf);
            if (str.length() > indexOf + 1) {
                this.b = str.substring(indexOf + 1);
            }
        }
    }
}
