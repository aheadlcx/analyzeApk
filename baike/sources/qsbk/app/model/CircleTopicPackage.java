package qsbk.app.model;

public class CircleTopicPackage {
    public int currentPos;
    public int[] picIndexs;
    public CircleTopic[] topics;

    public String getPic(int i) {
        return this.topics[i].getPicUrl(this.picIndexs[i]);
    }

    public int getCreateAt(int i) {
        return this.topics[i].createAt;
    }
}
