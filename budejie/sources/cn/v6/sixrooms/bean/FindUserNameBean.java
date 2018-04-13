package cn.v6.sixrooms.bean;

public class FindUserNameBean {
    private String usernameLeft;
    private String usernameRight;

    public String getUsernameLeft() {
        return this.usernameLeft;
    }

    public void setUsernameLeft(String str) {
        this.usernameLeft = str;
    }

    public String toString() {
        return "FindUserNameBean [usernameLeft=" + this.usernameLeft + ", usernameRight=" + this.usernameRight + "]";
    }

    public String getUsernameRight() {
        return this.usernameRight;
    }

    public void setUsernameRight(String str) {
        this.usernameRight = str;
    }
}
