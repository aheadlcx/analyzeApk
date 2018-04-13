package qsbk.app.model;

import java.io.Serializable;

public class MobileBrand implements Serializable {
    public static final String MOBILE_NAME_OF_HIDE = "Fight Club";
    private String a;
    private String b;

    public MobileBrand(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getName() {
        return this.a;
    }

    public String getCode() {
        return this.b;
    }

    public int hashCode() {
        return (this.a == null || this.b == null) ? super.hashCode() : this.a.hashCode() + this.b.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof MobileBrand)) {
            MobileBrand mobileBrand = (MobileBrand) obj;
            if (!(mobileBrand.a == null || this.a == null || mobileBrand.b == null || this.b == null)) {
                if (mobileBrand.a.equals(this.a) && mobileBrand.b.equals(this.b)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public String toString() {
        return "MobileBrand [name=" + this.a + "  code=" + this.b + "]";
    }
}
