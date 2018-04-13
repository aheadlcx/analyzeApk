package cn.xiaochuankeji.tieba.ui.voice.c;

public class a {
    public String a;
    public long b;
    public long c;

    public a(String str, long j) {
        this.a = str;
        this.c = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b != aVar.b) {
            return false;
        }
        if (this.a != null) {
            return this.a.equals(aVar.a);
        }
        if (aVar.a != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.a != null ? this.a.hashCode() : 0) * 31) + ((int) (this.b ^ (this.b >>> 32)));
    }
}
