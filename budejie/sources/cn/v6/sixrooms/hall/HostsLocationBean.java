package cn.v6.sixrooms.hall;

import java.util.ArrayList;
import java.util.List;

class HostsLocationBean extends BaseBean {
    private static final long serialVersionUID = 1;
    private String pid;
    private ArrayList<ProvinceNumBean> provinceNumAry;
    private String ptitle;
    private ArrayList<LiveItemBean> roomList;

    HostsLocationBean() {
    }

    public ArrayList<ProvinceNumBean> getProvinceNumAry() {
        return this.provinceNumAry;
    }

    public void setProvinceNumAry(ArrayList<ProvinceNumBean> arrayList) {
        this.provinceNumAry = arrayList;
    }

    public String getPtitle() {
        return this.ptitle;
    }

    public void setPtitle(String str) {
        this.ptitle = str;
    }

    public List<LiveItemBean> getRoomList() {
        return this.roomList;
    }

    public void setRoomList(ArrayList<LiveItemBean> arrayList) {
        this.roomList = arrayList;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }
}
