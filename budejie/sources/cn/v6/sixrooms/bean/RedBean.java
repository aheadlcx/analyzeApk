package cn.v6.sixrooms.bean;

public class RedBean {
    private String currentRed;
    private String outAllRed;

    public String getCurrentRed() {
        return this.currentRed;
    }

    public void setCurrentRed(String str) {
        this.currentRed = str;
    }

    public String getOutAllRed() {
        return this.outAllRed;
    }

    public void setOutAllRed(String str) {
        this.outAllRed = str;
    }

    public String toString() {
        return "RedBean [currentRed=" + this.currentRed + ", outAllRed=" + this.outAllRed + "]";
    }
}
