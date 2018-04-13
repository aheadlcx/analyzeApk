package cn.v6.sixrooms.bean;

class MemoBean {
    private String topAll;
    private String topTrend;
    private String topWeek;

    MemoBean() {
    }

    public String getTopTrend() {
        return this.topTrend;
    }

    public void setTopTrend(String str) {
        this.topTrend = str;
    }

    public String getTopAll() {
        return this.topAll;
    }

    public void setTopAll(String str) {
        this.topAll = str;
    }

    public String getTopWeek() {
        return this.topWeek;
    }

    public void setTopWeek(String str) {
        this.topWeek = str;
    }
}
