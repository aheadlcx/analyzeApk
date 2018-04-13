package qsbk.app.live;

import android.app.ProgressDialog;
import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.core.model.User;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.LiveRoom;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.utils.ToastAndDialog;

class a implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ ProgressDialog b;
    final /* synthetic */ LivePullLauncher c;

    a(LivePullLauncher livePullLauncher, int i, ProgressDialog progressDialog) {
        this.c = livePullLauncher;
        this.a = i;
        this.b = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        QsbkCommonVideo qsbkCommonVideo;
        String str = null;
        LiveRoom parse = LiveRoom.parse(jSONObject.optJSONObject("live"));
        boolean optBoolean = jSONObject.optBoolean("is_follow");
        if (parse == null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("user");
            if (optJSONObject != null) {
                str = String.valueOf(optJSONObject.optInt("id"));
                qsbkCommonVideo = new QsbkCommonVideo();
                qsbkCommonVideo.author = new User();
                qsbkCommonVideo.author.is_follow = optBoolean;
                qsbkCommonVideo.author.id = optJSONObject.optLong("remix_id", 0);
                qsbkCommonVideo.author.headurl = optJSONObject.optString("avatar_url");
                qsbkCommonVideo.author.name = optJSONObject.optString(QsbkDatabase.LOGIN);
                qsbkCommonVideo.author.origin = (long) optJSONObject.optInt("source");
                qsbkCommonVideo.author.platform_id = Long.parseLong(str);
                qsbkCommonVideo.status = 0;
                qsbkCommonVideo.game_id = (long) optJSONObject.optInt("game_id");
                if (this.c.roomId > 0) {
                    qsbkCommonVideo.room_id = this.c.roomId;
                }
                if (parse != null) {
                    parse.live_id = this.c.videoId;
                    this.c.with(parse).a(this.a);
                } else if (qsbkCommonVideo != null) {
                    onFailure(0, "数据解析失败");
                } else if (!this.c.fromAd && !TextUtils.isEmpty(str)) {
                    MyInfoActivity.launch(this.c.context, str, MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(9, str, "来自直播"));
                } else if (!this.c.fromAd || qsbkCommonVideo.author.id <= 0) {
                    onFailure(0, "直播已关闭");
                } else {
                    this.c.with(qsbkCommonVideo).a(this.a);
                }
                this.b.dismiss();
            }
        }
        qsbkCommonVideo = null;
        if (parse != null) {
            parse.live_id = this.c.videoId;
            this.c.with(parse).a(this.a);
        } else if (qsbkCommonVideo != null) {
            onFailure(0, "数据解析失败");
        } else {
            if (!this.c.fromAd) {
            }
            if (this.c.fromAd) {
            }
            onFailure(0, "直播已关闭");
        }
        this.b.dismiss();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.c.context, str).show();
        this.b.dismiss();
    }
}
