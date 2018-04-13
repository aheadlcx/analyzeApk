package mtopsdk.mtop.util;

public class j implements Cloneable {
    public long a;
    public long b;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    public int h;
    final /* synthetic */ h i;

    private j(h hVar) {
        this.i = hVar;
        this.h = 0;
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("rbReqTime=").append(this.d);
        stringBuilder.append(",mtopReqTime=").append(this.a);
        stringBuilder.append(",mtopJsonParseTime=").append(this.e);
        stringBuilder.append(",toMainThTime=").append(this.g);
        stringBuilder.append(",isCache=").append(this.h);
        stringBuilder.append(",beforeReqTime=").append(this.b);
        stringBuilder.append(",afterReqTime=").append(this.c);
        stringBuilder.append(",parseTime=").append(this.f);
        return stringBuilder.toString();
    }
}
