package cn.v6.sixrooms.bean;

public class SubLiveListBean extends MessageBean implements Comparable<SubLiveListBean> {
    private static final long serialVersionUID = 1;
    private String id;
    private int itemnum;
    private String memo;
    private String mid;
    private String mscFirst;
    private String mscName;
    private String salias;
    private String song_status;
    private long song_time;
    private int status;
    private String uid;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public long getSongTime() {
        return this.song_time;
    }

    public void setSongTime(long j) {
        this.song_time = j;
    }

    public int getItemnum() {
        return this.itemnum;
    }

    public void setItemnum(int i) {
        this.itemnum = i;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String str) {
        this.memo = str;
    }

    public String getSong_status() {
        return this.song_status;
    }

    public void setSong_status(String str) {
        this.song_status = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public String getMscFirst() {
        return this.mscFirst;
    }

    public void setMscFirst(String str) {
        this.mscFirst = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getMscName() {
        return this.mscName;
    }

    public void setMscName(String str) {
        this.mscName = str;
    }

    public String getSalias() {
        return this.salias;
    }

    public void setSalias(String str) {
        this.salias = str;
    }

    public String toString() {
        return "SubLiveListBean{song_status='" + this.song_status + '\'' + ", memo='" + this.memo + '\'' + ", itemnum=" + this.itemnum + ", uid='" + this.uid + '\'' + ", mid='" + this.mid + '\'' + ", mscName='" + this.mscName + '\'' + ", mscFirst='" + this.mscFirst + '\'' + ", tm='" + this.tm + '\'' + ", status=" + this.status + ", salias='" + this.salias + '\'' + '}';
    }

    public int compareTo(SubLiveListBean subLiveListBean) {
        return (int) (getSongTime() - subLiveListBean.getSongTime());
    }
}
