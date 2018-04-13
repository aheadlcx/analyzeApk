package qsbk.app.adapter;

import java.io.Serializable;

public class EditUserInfoAdapter$UserInfoItem {
    private final String a;
    private final String b;
    private final boolean c;
    private String d;
    private Serializable e;

    public EditUserInfoAdapter$UserInfoItem(String str, String str2, String str3, Serializable serializable, boolean z) {
        this.a = str;
        this.d = str2;
        this.b = str3;
        this.e = serializable;
        this.c = z;
    }

    public String getDisplayDesription() {
        return this.a;
    }

    public String getDisplayValue() {
        return this.d;
    }

    public void setDisplayValue(String str) {
        this.d = str;
    }

    public String getDefaultDisplayValue() {
        return this.b;
    }

    public Serializable getInnerValue() {
        return this.e;
    }

    public void setInnerValue(Serializable serializable) {
        this.e = serializable;
    }

    public boolean isUserEditable() {
        return this.c;
    }
}
