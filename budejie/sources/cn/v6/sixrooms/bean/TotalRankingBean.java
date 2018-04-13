package cn.v6.sixrooms.bean;

import java.util.List;

public class TotalRankingBean {
    private List<RankingBean> beans;

    public List<RankingBean> getBeans() {
        return this.beans;
    }

    public void setBeans(List<RankingBean> list) {
        this.beans = list;
    }

    public String toString() {
        return "TotalRankingBean [beans=" + this.beans + "]";
    }
}
