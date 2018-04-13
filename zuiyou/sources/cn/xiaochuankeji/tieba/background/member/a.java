package cn.xiaochuankeji.tieba.background.member;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import com.iflytek.aiui.AIUIConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends d<MemberCommentInfo> {
    private long a;
    private long b = 0;
    private String c = "new";
    private boolean d = false;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public a(long j) {
        this.a = j;
    }

    public void a(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    protected b getHttpEngine() {
        return cn.xiaochuankeji.tieba.background.a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/user/reviews");
    }

    protected MemberCommentInfo a(JSONObject jSONObject) {
        return new MemberCommentInfo(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("uid", this.a);
            jSONObject.put(AIUIConstant.KEY_TAG, this.c);
            jSONObject.put("t", this.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        this.b = jSONObject.optLong("t");
        if (jSONObject.optInt("more") != 1) {
            z = false;
        }
        this.d = z;
        super.handleQuerySuccResult(jSONObject);
    }

    public void refresh() {
        this.b = 0;
        super.refresh();
    }

    public void a(long j) {
        for (int i = 0; i < itemCount(); i++) {
            MemberCommentInfo memberCommentInfo = (MemberCommentInfo) itemAt(i);
            if (memberCommentInfo.comment._id == j) {
                this._items.remove(memberCommentInfo);
                notifyListUpdate();
                return;
            }
        }
    }

    public boolean hasMore() {
        return this.d;
    }
}
