package cn.xiaochuankeji.tieba.ui.my.favorite;

import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class FavoriteBean implements Serializable {
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "id")
    public long favorId;
    @JSONField(name = "mid")
    public long memberId;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "post_count")
    public int postCount;
    @JSONField(name = "ut")
    public long ut;

    static Favorite getFavoriteFromFavoriteBean(FavoriteBean favoriteBean) {
        try {
            return new Favorite(new JSONObject(JSON.toJSONString(favoriteBean)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
