package qsbk.app.core.model;

import java.io.Serializable;

public class FamilyInfo implements Serializable {
    public String b;
    public int c;
    public long fid;
    public int fl;
    public String n;
    public String r;

    public boolean isFamilyCreator() {
        return this.c == 1;
    }

    public String getFamilyName() {
        return this.n;
    }

    public String getFamilyBadge() {
        return this.b;
    }

    public long getFamilyId() {
        return this.fid;
    }

    public String getFamilyImg() {
        return this.r;
    }
}
