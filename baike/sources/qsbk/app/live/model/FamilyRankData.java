package qsbk.app.live.model;

import com.google.gson.annotations.SerializedName;

public class FamilyRankData {
    public String b;
    public String c;
    public long f;
    public long i;
    @SerializedName("fl")
    public int l;
    public String n;
    public int r;
    public String t;
    public int u;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.i != ((FamilyRankData) obj).i) {
            return false;
        }
        return true;
    }
}
