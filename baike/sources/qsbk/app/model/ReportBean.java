package qsbk.app.model;

import java.io.Serializable;

public class ReportBean implements Serializable {
    private String a;
    private int b;

    public ReportBean(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public String getName() {
        return this.a;
    }

    public void setName(String str) {
        this.a = str;
    }

    public int getValue() {
        return this.b;
    }

    public void setValue(int i) {
        this.b = i;
    }

    public int hashCode() {
        return (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ReportBean reportBean = (ReportBean) obj;
        if (this.a == null) {
            if (reportBean.a != null) {
                return false;
            }
        } else if (!this.a.equals(reportBean.a)) {
            return false;
        }
        if (this.b != reportBean.b) {
            return false;
        }
        return true;
    }
}
