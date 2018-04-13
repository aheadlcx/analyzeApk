package cn.v6.sixrooms.bean;

public class WelcomeBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String alias;
    private int driver;
    private String msg;
    private String pngcar;
    private String priv;
    private String prop;
    private String rich;
    private String rid;
    private String rn;
    private String sf;
    private String uid;

    public static long getSerialVersionUID() {
        return 1;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getProp() {
        return this.prop;
    }

    public void setProp(String str) {
        this.prop = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public int getDriver() {
        return this.driver;
    }

    public void setDriver(int i) {
        this.driver = i;
    }

    public String getPriv() {
        return this.priv;
    }

    public void setPriv(String str) {
        this.priv = str;
    }

    public String getPngcar() {
        return this.pngcar;
    }

    public void setPngcar(String str) {
        this.pngcar = str;
    }

    public String getRn() {
        return this.rn;
    }

    public void setRn(String str) {
        this.rn = str;
    }

    public String getSf() {
        return this.sf;
    }

    public void setSf(String str) {
        this.sf = str;
    }

    public String getRich() {
        return this.rich;
    }

    public void setRich(String str) {
        this.rich = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }
}
