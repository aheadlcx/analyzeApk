package cn.v6.sixrooms.bean;

public class NumberBean implements Comparable<NumberBean> {
    private String desc;
    private int number;

    public NumberBean(int i, String str) {
        this.number = i;
        this.desc = str;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int i) {
        this.number = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public int compareTo(NumberBean numberBean) {
        if (this.number > numberBean.number) {
            return 1;
        }
        if (this.number < numberBean.number) {
            return -1;
        }
        return 0;
    }
}
