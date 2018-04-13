package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import java.util.Date;

@NotThreadSafe
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private String a;
    private int[] b;
    private boolean c;

    public BasicClientCookie2(String str, String str2) {
        super(str, str2);
    }

    public int[] getPorts() {
        return this.b;
    }

    public void setPorts(int[] iArr) {
        this.b = iArr;
    }

    public String getCommentURL() {
        return this.a;
    }

    public void setCommentURL(String str) {
        this.a = str;
    }

    public void setDiscard(boolean z) {
        this.c = z;
    }

    public boolean isPersistent() {
        return !this.c && super.isPersistent();
    }

    public boolean isExpired(Date date) {
        return this.c || super.isExpired(date);
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 basicClientCookie2 = (BasicClientCookie2) super.clone();
        if (this.b != null) {
            basicClientCookie2.b = (int[]) this.b.clone();
        }
        return basicClientCookie2;
    }
}
