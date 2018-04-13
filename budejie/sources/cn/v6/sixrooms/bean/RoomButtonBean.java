package cn.v6.sixrooms.bean;

public class RoomButtonBean {
    private int iconID;
    private boolean selected = false;
    private boolean showPopuwindow = false;
    private boolean visiable = true;

    public RoomButtonBean(int i, boolean z, boolean z2) {
        this.iconID = i;
        this.showPopuwindow = z;
        this.selected = z2;
    }

    public RoomButtonBean(int i, boolean z) {
        this.iconID = i;
        this.visiable = z;
    }

    public int getIconID() {
        return this.iconID;
    }

    public void setIconID(int i) {
        this.iconID = i;
    }

    public boolean isVisiable() {
        return this.visiable;
    }

    public void setVisiable(boolean z) {
        this.visiable = z;
    }

    public boolean isShowPopuwindow() {
        return this.showPopuwindow;
    }

    public void setShowPopuwindow(boolean z) {
        this.showPopuwindow = z;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String toString() {
        return "RoomButtonBean [iconID=" + this.iconID + ", visiable=" + this.visiable + ", showPopuwindow=" + this.showPopuwindow + ", selected=" + this.selected + "]";
    }
}
