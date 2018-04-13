package mtopsdk.mtop.common;

import java.util.Map;

public class j extends h {
    private int a;
    private Map b;

    public j(int i, Map map) {
        this.a = i;
        this.b = map;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MtopHeaderEvent [");
        stringBuilder.append("code=").append(this.a);
        stringBuilder.append(", header=").append(this.b);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
