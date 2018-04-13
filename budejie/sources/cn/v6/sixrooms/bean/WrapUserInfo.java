package cn.v6.sixrooms.bean;

import java.util.ArrayList;

public class WrapUserInfo extends MessageBean {
    private static final long serialVersionUID = 1;
    private ArrayList<UserInfoBean> admList;
    private ArrayList<UserInfoBean> allAdmList;
    private ArrayList<UserInfoBean> allList;
    private ArrayList<UserInfoBean> fansCardList;
    private ArrayList<UserInfoBean> livList;
    private String ntvsn;
    private String num;
    private ArrayList<UserInfoBean> safeList;
    private String typeID;

    public ArrayList<UserInfoBean> getFansCardList() {
        return this.fansCardList;
    }

    public void setFansCardList(ArrayList<UserInfoBean> arrayList) {
        this.fansCardList = arrayList;
    }

    public String getNtvsn() {
        return this.ntvsn;
    }

    public void setNtvsn(String str) {
        this.ntvsn = str;
    }

    public ArrayList<UserInfoBean> getAllList() {
        return this.allList;
    }

    public void setAllList(ArrayList<UserInfoBean> arrayList) {
        this.allList = arrayList;
    }

    public ArrayList<UserInfoBean> getLivList() {
        return this.livList;
    }

    public void setLivList(ArrayList<UserInfoBean> arrayList) {
        this.livList = arrayList;
    }

    public ArrayList<UserInfoBean> getAdmList() {
        return this.admList;
    }

    public void setAdmList(ArrayList<UserInfoBean> arrayList) {
        this.admList = arrayList;
    }

    public ArrayList<UserInfoBean> getSafeList() {
        return this.safeList;
    }

    public void setSafeList(ArrayList<UserInfoBean> arrayList) {
        this.safeList = arrayList;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getTypeID() {
        return this.typeID;
    }

    public void setTypeID(String str) {
        this.typeID = str;
    }

    public ArrayList<UserInfoBean> getAllAdmList() {
        return this.allAdmList;
    }

    public void setAllAdmList(ArrayList<UserInfoBean> arrayList) {
        this.allAdmList = arrayList;
    }

    public String toString() {
        return "WrapUserInfo [num=" + this.num + ", typeID=" + this.typeID + ", allList=" + this.allList + ", livList=" + this.livList + ", admList=" + this.admList + ", safeList=" + this.safeList + ", allAdmList=" + this.allAdmList + "]";
    }
}
