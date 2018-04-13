package mtopsdk.mtop.antiattack;

class f {
    public String a;
    public long b;
    public long c;

    public f(String str, long j, long j2) {
        this.a = str;
        this.b = j;
        this.c = j2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LockedEntity [");
        stringBuilder.append("key=").append(this.a);
        stringBuilder.append(", lockStartTime=").append(this.b);
        stringBuilder.append(", lockInterval=").append(this.c);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
