package cn.v6.sixrooms.bean;

public class LiveBroadcastBean extends MessageBean {
    private LiveUserInfo current;
    private LiveUserInfo down;
    private LiveUserInfo up;

    public LiveUserInfo getDown() {
        return this.down;
    }

    public void setDown(LiveUserInfo liveUserInfo) {
        this.down = liveUserInfo;
    }

    public LiveUserInfo getUp() {
        return this.up;
    }

    public void setUp(LiveUserInfo liveUserInfo) {
        this.up = liveUserInfo;
    }

    public LiveUserInfo getCurrent() {
        return this.current;
    }

    public void setCurrent(LiveUserInfo liveUserInfo) {
        this.current = liveUserInfo;
    }
}
