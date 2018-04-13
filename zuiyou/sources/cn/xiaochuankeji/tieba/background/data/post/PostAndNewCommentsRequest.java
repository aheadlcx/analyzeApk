package cn.xiaochuankeji.tieba.background.data.post;

import android.text.TextUtils;
import cn.htjyb.netlib.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.d.a;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.data.Comment;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostAndNewCommentsRequest implements a {
    private final long _ID;
    private Listener _listener;
    private String _srcType = null;
    private final long mCommentId;
    private final String mType;

    public interface Listener {
        void onQueryPostAndNewCommentsFinish(boolean z, JSONObject jSONObject, Post post, ArrayList<Comment> arrayList, boolean z2, long j, String str);
    }

    public void setListener(Listener listener) {
        this._listener = listener;
    }

    public PostAndNewCommentsRequest(long j, long j2, String str) {
        this._ID = j;
        this.mCommentId = j2;
        this.mType = str;
    }

    public void setSrcType(String str) {
        this._srcType = str;
    }

    public void query() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pid", this._ID);
            jSONObject.put("rid", this.mCommentId);
            jSONObject.put("type", this.mType);
            jSONObject.put("offset", 0);
            if (!TextUtils.isEmpty(this._srcType)) {
                jSONObject.put("from", this._srcType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/query_preview"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this).b();
    }

    public void onTaskFinish(d dVar) {
        b.a aVar = dVar.c;
        if (aVar.a) {
            if (this._listener != null) {
                JSONObject jSONObject = aVar.c;
                ArrayList parseCommentJSONObject = parseCommentJSONObject(jSONObject.optJSONArray("list"));
                JSONObject optJSONObject = jSONObject.optJSONObject("post");
                this._listener.onQueryPostAndNewCommentsFinish(true, optJSONObject, new Post(optJSONObject), parseCommentJSONObject, jSONObject.optInt("more") == 1, (long) jSONObject.optInt("offset"), null);
            }
        } else if (this._listener != null) {
            this._listener.onQueryPostAndNewCommentsFinish(false, null, null, null, false, 0, aVar.c());
        }
    }

    private ArrayList<Comment> parseCommentJSONObject(JSONArray jSONArray) {
        ArrayList<Comment> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(new Comment(jSONArray.optJSONObject(i)));
        }
        return arrayList;
    }
}
