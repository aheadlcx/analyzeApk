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

public class PostAndCommentsRequest implements a {
    private long _ID;
    private OnQueryPostFinishListener _listener;
    private String _srcArea = null;
    private String _srcType = null;

    public interface OnQueryPostFinishListener {
        void onQueryPostFinish(boolean z, JSONObject jSONObject, ArrayList<Comment> arrayList, ArrayList<Comment> arrayList2, boolean z2, boolean z3, String str);
    }

    public void registerOnQueryPostFinishListener(OnQueryPostFinishListener onQueryPostFinishListener) {
        this._listener = onQueryPostFinishListener;
    }

    public void unregisterOnQueryPostFinishListener() {
        this._listener = null;
    }

    public PostAndCommentsRequest(long j) {
        this._ID = j;
    }

    public void setSrcType(String str) {
        this._srcType = str;
    }

    public void setArea(String str) {
        this._srcArea = str;
    }

    public void query() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            jSONObject.put("pid", this._ID);
            if (!TextUtils.isEmpty(this._srcType)) {
                jSONObject.put("from", this._srcType);
            }
            if (!TextUtils.isEmpty(this._srcArea)) {
                jSONObject.put("area", this._srcArea);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/detail"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this).b();
    }

    public void onTaskFinish(d dVar) {
        boolean z = false;
        b.a aVar = dVar.c;
        if (aVar.a) {
            if (this._listener != null) {
                JSONObject jSONObject = aVar.c;
                JSONArray optJSONArray = jSONObject.optJSONArray("hotreviews");
                JSONArray optJSONArray2 = jSONObject.optJSONArray("newreviews");
                ArrayList parseCommentJSONObject = parseCommentJSONObject(optJSONArray);
                ArrayList parseCommentJSONObject2 = parseCommentJSONObject(optJSONArray2);
                JSONObject optJSONObject = jSONObject.optJSONObject("post");
                boolean z2 = jSONObject.optInt("morehot") == 1;
                if (jSONObject.optInt("more") == 1) {
                    z = true;
                }
                this._listener.onQueryPostFinish(true, optJSONObject, parseCommentJSONObject, parseCommentJSONObject2, z2, z, null);
            }
        } else if (this._listener != null) {
            this._listener.onQueryPostFinish(false, null, null, null, false, false, aVar.c());
        }
    }

    private ArrayList<Comment> parseCommentJSONObject(JSONArray jSONArray) {
        ArrayList<Comment> arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(new Comment(jSONArray.optJSONObject(i)));
        }
        return arrayList;
    }
}
