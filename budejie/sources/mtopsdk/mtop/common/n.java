package mtopsdk.mtop.common;

public class n extends h {
    String a;
    int b;
    int c;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MtopProgressEvent [");
        stringBuilder.append("desc=").append(this.a);
        stringBuilder.append(", size=").append(this.b);
        stringBuilder.append(", total=").append(this.c);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
