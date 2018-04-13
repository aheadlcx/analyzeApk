package mtopsdk.mtop.common;

import mtopsdk.mtop.domain.MtopResponse;

public class i extends h {
    public MtopResponse a;

    public i(MtopResponse mtopResponse) {
        this.a = mtopResponse;
    }

    public MtopResponse a() {
        return this.a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MtopFinishEvent [");
        stringBuilder.append("mtopResponse").append(this.a).append("]");
        return stringBuilder.toString();
    }
}
