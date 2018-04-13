package qsbk.app;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.LiveRoom;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.utils.LiveUtil;

class i implements SimpleCallBack {
    final /* synthetic */ Callback a;
    final /* synthetic */ g b;

    i(g gVar, Callback callback) {
        this.b = gVar;
        this.a = callback;
    }

    public void onSuccess(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("live");
        CommonVideo commonVideo = null;
        boolean optBoolean = jSONObject.optBoolean("is_follow");
        if (optJSONObject == null) {
            JSONObject optJSONObject2 = jSONObject.optJSONObject("user");
            if (optJSONObject2 != null) {
                String valueOf = String.valueOf(optJSONObject2.optInt("id"));
                commonVideo = new QsbkCommonVideo();
                commonVideo.author = new User();
                commonVideo.author.is_follow = optBoolean;
                commonVideo.author.id = optJSONObject2.optLong("remix_id", 0);
                commonVideo.author.headurl = optJSONObject2.optString("avatar_url");
                commonVideo.author.name = optJSONObject2.optString(QsbkDatabase.LOGIN);
                commonVideo.author.origin = (long) optJSONObject2.optInt("source");
                commonVideo.author.platform_id = Long.parseLong(valueOf);
                commonVideo.status = 0;
            }
        }
        if (optJSONObject != null) {
            if (this.a != null) {
                try {
                    this.a.onSuccess(new JSONObject(LiveUtil.convert2CommonVideo(LiveRoom.parse(optJSONObject)).toJson()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (commonVideo == null) {
            onFailure(-1, "数据解析失败");
        } else if (this.a != null) {
            try {
                this.a.onSuccess(new JSONObject(commonVideo.toJson()));
            } catch (JSONException e2) {
                e2.printStackTrace();
                onFailure(-1, "数据解析失败");
            }
        }
    }

    public void onFailure(int i, String str) {
        if (this.a != null) {
            this.a.onFailed(i, str);
        }
    }
}
