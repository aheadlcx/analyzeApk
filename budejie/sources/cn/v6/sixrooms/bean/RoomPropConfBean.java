package cn.v6.sixrooms.bean;

import java.util.ArrayList;
import java.util.List;

public class RoomPropConfBean {
    private List<String> ban = new ArrayList();
    private List<String> com = new ArrayList();

    public List<String> getBan() {
        return this.ban;
    }

    public void setBan(List<String> list) {
        this.ban = list;
    }

    public List<String> getCom() {
        return this.com;
    }

    public void setCom(List<String> list) {
        this.com = list;
    }

    public String toString() {
        return "RoomPropConfBean{com=" + this.com + ", ban=" + this.ban + '}';
    }
}
