package cn.tatagou.sdk.pojo;

public class RcmmSpecial {
    private CateLimit cateLimit;
    private DefaultOrder defaultOrder;
    private LvCate lv1Cate;
    private LvCate lv2Cate;
    private Novelty novelty;
    private Sex sex;

    public CateLimit getCateLimit() {
        return this.cateLimit;
    }

    public Sex getSex() {
        return this.sex;
    }

    public Novelty getNovelty() {
        return this.novelty;
    }

    public LvCate getLv1Cate() {
        return this.lv1Cate;
    }

    public LvCate getLv2Cate() {
        return this.lv2Cate;
    }

    public DefaultOrder getDefaultOrder() {
        return this.defaultOrder;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setNovelty(Novelty novelty) {
        this.novelty = novelty;
    }

    public void setLv1Cate(LvCate lvCate) {
        this.lv1Cate = lvCate;
    }

    public void setLv2Cate(LvCate lvCate) {
        this.lv2Cate = lvCate;
    }

    public void setDefaultOrder(DefaultOrder defaultOrder) {
        this.defaultOrder = defaultOrder;
    }

    public void setCateLimit(CateLimit cateLimit) {
        this.cateLimit = cateLimit;
    }
}
