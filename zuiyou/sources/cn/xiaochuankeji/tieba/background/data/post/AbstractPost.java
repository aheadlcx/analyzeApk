package cn.xiaochuankeji.tieba.background.data.post;

import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractPost {
    public static final int CLASS_TYPE_ADS_ITEM = 4;
    public static final int CLASS_TYPE_API_ADS_ITEM = 5;
    public static final int CLASS_TYPE_MOMENT_ITEM = 3;
    public static final int CLASS_TYPE_REAL_POST = 0;
    public static final int CLASS_TYPE_SUBJECTS_ITEM = 2;
    public static final int CLASS_TYPE_TOPICS_ITEM = 1;
    protected int c_type = 0;

    public abstract int classType();

    public abstract long getMemberId();

    public abstract void setFollowStatus(int i);

    public boolean isRealPost() {
        return classType() == 0 || this.c_type == 1;
    }

    public boolean isUgcPost() {
        return classType() == 3 || this.c_type == 3;
    }

    public boolean isGDTAds() {
        return classType() == 4;
    }

    public boolean isApiHardAd() {
        return this.c_type == 8;
    }

    public boolean isApiSoftAd() {
        return this.c_type == 7;
    }

    public void parseBaseInfo(JSONObject jSONObject) {
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("AbstractPost_classType", classType());
        return jSONObject;
    }

    public int getCtype() {
        return this.c_type;
    }

    public static AbstractPost create(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("AbstractPost_classType", 0);
        AbstractPost post;
        if (optInt == 0) {
            post = new Post(jSONObject);
            post.c_type = 1;
            return post;
        } else if (optInt == 1) {
            return new RecommendedTopics(jSONObject);
        } else {
            if (optInt == 2) {
                return new Subject(jSONObject);
            }
            if (optInt != 3) {
                return null;
            }
            post = new Moment(jSONObject);
            post.c_type = 3;
            return post;
        }
    }

    public static AbstractPost createByCtype(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("c_type", 1);
        if (optInt == 0) {
            return null;
        }
        AbstractPost post;
        switch (optInt) {
            case 1:
                post = new Post(jSONObject);
                post.c_type = optInt;
                return post;
            case 3:
                post = new Moment(jSONObject);
                post.c_type = optInt;
                return post;
            case 7:
                post = AdvertismentSoftBean.createByJson(jSONObject);
                post.c_type = optInt;
                return post;
            case 8:
                post = AdvertismentBean.createByJson(jSONObject);
                post.c_type = optInt;
                return post;
            default:
                return null;
        }
    }
}
