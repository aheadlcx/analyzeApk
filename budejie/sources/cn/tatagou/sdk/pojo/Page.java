package cn.tatagou.sdk.pojo;

import java.util.List;

public class Page<T extends CommonResponseResult> {
    private int current_page;
    private List<T> data;
    private int last_page;
    private int per_page;
    private int total;

    public int getCurrent_page() {
        return this.current_page;
    }

    public void setCurrent_page(int i) {
        this.current_page = i;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> list) {
        this.data = list;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public int getLast_page() {
        return this.last_page;
    }

    public void setLast_page(int i) {
        this.last_page = i;
    }

    public int getPer_page() {
        return this.per_page;
    }

    public void setPer_page(int i) {
        this.per_page = i;
    }
}
