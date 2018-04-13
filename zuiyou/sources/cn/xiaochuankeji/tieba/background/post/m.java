package cn.xiaochuankeji.tieba.background.post;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import org.json.JSONObject;

public abstract class m extends d<AbstractPost> {
    public void remove(AbstractPost abstractPost) {
        this._items.remove(abstractPost);
        notifyListUpdate();
    }

    public void remove(int i) {
        this._items.remove(i);
        notifyListUpdate();
    }

    public void add(AbstractPost abstractPost) {
        this._items.add(0, abstractPost);
        notifyListUpdate();
    }

    public int getLoadMoreItemIndex() {
        return -1;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected AbstractPost parseItem(JSONObject jSONObject) {
        return new Post(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }
}
