package qsbk.app.live.model;

import java.io.Serializable;

public class FamilyMemberData implements Serializable {
    public String a;
    public int f;
    public int l;
    public String n;
    public long p;
    public int s;
    public String t;
    public long u;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FamilyMemberData familyMemberData = (FamilyMemberData) obj;
        if (this.u == familyMemberData.u && this.s == familyMemberData.s) {
            return true;
        }
        return false;
    }
}
