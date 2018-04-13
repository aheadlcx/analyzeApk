package qsbk.app.share.message;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.fragments.ShareToImType;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.EventWindow;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;

class i implements OnClickListener {
    final /* synthetic */ ContactListItem a;
    final /* synthetic */ RecentContactsFragment b;

    i(RecentContactsFragment recentContactsFragment, ContactListItem contactListItem) {
        this.b = recentContactsFragment;
        this.a = contactListItem;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.b.c.getInt("share_type", ShareToImType.TYPE_ARTICLE.getValue());
        LogUtil.e("type:" + i2);
        String makeMsgData;
        String qiushiShareSummary;
        if (i2 == ShareToImType.TYPE_CIRCLE_ARTICLE.getValue()) {
            makeMsgData = ShareUtils.makeMsgData(this.b.c);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.b.c);
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiuyouCircleShare(this.a, makeMsgData, qiushiShareSummary, null, true);
        } else if (i2 == ShareToImType.TYPE_LIVE.getValue()) {
            CommonVideo commonVideo = (CommonVideo) this.b.c.getSerializable(EventWindow.JUMP_LIVE_ROOM);
            r4 = new JSONObject();
            qiushiShareSummary = commonVideo.author.name + "正在直播，颜值爆表~快来一起看！" + commonVideo.content;
            try {
                r4.put("content", qiushiShareSummary);
                if (commonVideo instanceof QsbkCommonVideo) {
                    r4.put("live_id", ((QsbkCommonVideo) commonVideo).live_id);
                } else {
                    r4.put("live_id", commonVideo.getAuthorId());
                }
                r4.put("pic_url", commonVideo.getPicUrl());
                r4.put("title", qiushiShareSummary);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            makeMsgData = r4.toString();
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendLiveShare(this.a, makeMsgData, qiushiShareSummary, null, true);
        } else if (i2 == ShareToImType.TYPE_WEB.getValue() || i2 == ShareToImType.TYPE_LIVE_ACTIVITY.getValue() || i2 == ShareToImType.TYPE_NEWS.getValue()) {
            ShareMsgItem shareMsgItem = (ShareMsgItem) this.b.c.getSerializable("share_item");
            if (shareMsgItem != null) {
                Object jSONObject;
                String str = "";
                str = "";
                if (i2 == ShareToImType.TYPE_WEB.getValue()) {
                    jSONObject = shareMsgItem.toJson().toString();
                    qiushiShareSummary = shareMsgItem.content;
                } else if (i2 == ShareToImType.TYPE_LIVE_ACTIVITY.getValue()) {
                    jSONObject = shareMsgItem.toJson().toString();
                    qiushiShareSummary = shareMsgItem.title;
                } else {
                    try {
                        r4 = new JSONObject();
                        r4.put("title", shareMsgItem.title);
                        r4.put("image_url", shareMsgItem.imageUrl);
                        r4.put("news_id", shareMsgItem.news_id);
                        str = r4.toString();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    qiushiShareSummary = shareMsgItem.title;
                    makeMsgData = str;
                }
                if (!TextUtils.isEmpty(jSONObject)) {
                    QsbkApp.getInstance();
                    UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendWebShare(this.a, jSONObject, qiushiShareSummary, null, true, i2);
                }
            } else {
                return;
            }
        } else if (i2 == ShareToImType.TYPE_QSJX.getValue()) {
            Qsjx qsjx = (Qsjx) this.b.c.getSerializable(QYQShareInfo.TYPE_QSJX);
            if (qsjx != null) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQsjxShare(this.a, qsjx, null, true);
            }
        } else if (i2 == ShareToImType.TYPE_QIUSHI_TOPIC.getValue()) {
            QiushiTopic qiushiTopic = (QiushiTopic) this.b.c.getSerializable(SplashAdItem.AD_QIUSHI_TOPIC);
            if (qiushiTopic != null) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiushiTopicShare(this.a, qiushiTopic, null, true);
            }
        } else if (this.b.c.getBoolean("isFromCircleTopic", false)) {
            makeMsgData = ShareUtils.makeShareMsgData(this.b.c);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.b.c);
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendCircleTopicShare(this.a, makeMsgData, qiushiShareSummary, null, true);
        } else {
            makeMsgData = ShareUtils.makeMsgData(this.b.c);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.b.c);
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiushiShare(this.a, makeMsgData, qiushiShareSummary, null, true);
        }
        ToastAndDialog.makePositiveToast(this.b.getActivity(), "已分享给" + this.a.name, Integer.valueOf(0)).show();
        this.b.getActivity().finish();
    }
}
