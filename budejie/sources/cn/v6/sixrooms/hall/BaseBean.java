package cn.v6.sixrooms.hall;

import java.io.Serializable;

public class BaseBean implements Serializable {
    private boolean isSelect;
    private int position;

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
