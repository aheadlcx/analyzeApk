package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingBoxer extends MessageBean {
    private String age;
    private String name;
    private String pic;
    private long vote;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String str) {
        this.age = str;
    }

    public long getVote() {
        return this.vote;
    }

    public void setVote(long j) {
        this.vote = j;
    }

    public String toString() {
        return "BoxingBoxer [name=" + this.name + ", pic=" + this.pic + ", age=" + this.age + ", vote=" + this.vote + "]";
    }
}
