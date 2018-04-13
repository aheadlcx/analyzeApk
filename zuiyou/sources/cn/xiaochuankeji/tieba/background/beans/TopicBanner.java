package cn.xiaochuankeji.tieba.background.beans;

public class TopicBanner {
    public static final int BANNER_TYPE_H5 = 3;
    public static final int BANNER_TYPE_POST = 9;
    public static final int BANNER_TYPE_TOPIC = 1;
    public String activityUrl;
    public long id;
    public String imageUrl;
    public int type;

    public static boolean isSupport(int i) {
        return i == 1 || i == 3 || i == 9;
    }
}
