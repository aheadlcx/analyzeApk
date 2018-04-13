package cn.tatagou.sdk.pojo;

import java.util.List;

public class RcmmParam extends CommonResponseResult {
    private List<String> rcmmCates;
    private RcmmSpecial rcmmSpecials;

    public List<String> getRcmmCates() {
        return this.rcmmCates;
    }

    public void setRcmmCates(List<String> list) {
        this.rcmmCates = list;
    }

    public RcmmSpecial getRcmmSpecials() {
        return this.rcmmSpecials;
    }

    public void setRcmmSpecials(RcmmSpecial rcmmSpecial) {
        this.rcmmSpecials = rcmmSpecial;
    }
}
