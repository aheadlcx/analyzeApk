package qsbk.app;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.ImagesPickerActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.provider.UserInfoProvider;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.GroupConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMChatBaseActivityEx;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.live.DataHelper;
import qsbk.app.live.share.ImageShareActivity;
import qsbk.app.live.share.LiveShareActivity;
import qsbk.app.live.ui.LivePullActivity;
import qsbk.app.pay.ui.PayActivity;
import qsbk.app.share.LiveLoginActivity;
import qsbk.app.utils.LiveUtil;

class g extends UserInfoProvider {
    final /* synthetic */ QsbkApp a;

    g(QsbkApp qsbkApp) {
        this.a = qsbkApp;
    }

    public void toShare(Activity activity, CommonVideo commonVideo) {
        super.toShare(activity, commonVideo, "live");
    }

    public void toShare(Activity activity, CommonVideo commonVideo, String str, int i) {
        if (activity != null && !activity.isFinishing() && commonVideo != null && commonVideo.share != null) {
            Intent intent = new Intent();
            intent.setClass(activity, LiveShareActivity.class);
            intent.putExtra("video", commonVideo);
            intent.putExtra("sns", i);
            intent.putExtra("from", str);
            if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
                activity.startActivity(intent);
            } else {
                activity.startActivityForResult(intent, LiveShareActivity.SHARE_NO_LOGIN);
            }
        }
    }

    public void shareImage(Activity activity, String str, int i) {
        if (QsbkApp.currentUser != null) {
            Intent intent = new Intent();
            intent.setClass(activity, ImageShareActivity.class);
            intent.putExtra("imgUrl", str);
            intent.putExtra("type", i);
            activity.startActivity(intent);
            return;
        }
        ActionBarLoginActivity.launch(QsbkApp.getInstance());
    }

    public void toLogin(Activity activity, int i) {
        if (activity != null) {
            LiveLoginActivity.launch(activity, i);
        }
    }

    public void onLogout(String str) {
    }

    public User getUser() {
        return LiveUtil.convert2User(QsbkApp.currentUser);
    }

    public void setUser(User user) {
        LiveUtil.updateUserInfo(user);
    }

    public String getToken() {
        return QsbkApp.currentUser != null ? QsbkApp.currentUser.token : "";
    }

    public void setToken(String str) {
    }

    public boolean isLogin() {
        return QsbkApp.currentUser != null;
    }

    public long getCertificate() {
        return DataHelper.getInstance().getCertificate();
    }

    public void setCertificate(long j) {
        DataHelper.getInstance().setCertificate(j);
    }

    public long getBalance() {
        return DataHelper.getInstance().getBalance();
    }

    public void setBalance(long j) {
        DataHelper.getInstance().setBalance(j);
    }

    public void toUserPage(Activity activity, User user) {
        String l = Long.toString(user.getPlatformId());
        MyInfoActivity.launch(activity, l, MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(9, l, "来自直播"));
    }

    public void toPay(Activity activity, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, PayActivity.class);
        activity.startActivityForResult(intent, i);
    }

    public void toMain(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    public void networkRequest(String str, Map<String, String> map, Callback callback) {
        String str2;
        if ("recommendLive".equals(str)) {
            str2 = (String) map.get("room");
            new SimpleHttpTask(String.format(Constants.LIVE_RECOMMEND_FOR_REMIX, new Object[]{Integer.valueOf(3), Integer.valueOf(1), str2}), new h(this, callback)).execute();
        } else if ("liveDetail".equals(str)) {
            str2 = (String) map.get("creator_id");
            String str3 = (String) map.get("creator_source");
            str2 = String.format(Constants.LIVE_INFO, new Object[]{str2});
            if (str2.contains("?")) {
                str2 = str2 + "&origin=" + str3;
            } else {
                str2 = str2 + "?origin=" + str3;
            }
            new SimpleHttpTask(str2, new i(this, callback)).execute();
        }
    }

    public void toLive(Activity activity, String str, String str2, long j) {
        Intent intent = new Intent(activity, LivePullActivity.class);
        intent.putExtra("liveUserId", str);
        intent.putExtra("liveUserSource", j);
        intent.putExtra("livePlatformId", str2);
        activity.startActivity(intent);
    }

    public void toConversation(Activity activity, String str, String str2) {
        if (TextUtils.equals("duobao_official", str)) {
            Intent intent = new Intent(activity, ConversationActivity.class);
            QsbkApp.getInstance();
            intent.putExtra("user_id", QsbkApp.currentUser.userId);
            intent.putExtra(IMChatBaseActivity.USER_TYPE, 1);
            intent.putExtra("to_id", "32879940");
            intent.putExtra(IMChatBaseActivity.TO_ICON, "2016112418432951.JPEG");
            intent.putExtra(IMChatBaseActivity.TO_NICK, "糗商城");
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            this.a.startActivity(intent);
        } else if (TextUtils.equals("live", str)) {
            ChatMsg chatMsg = new ChatMsg();
            try {
                chatMsg.parseFromJSONObject(new JSONObject(str2));
                if (!TextUtils.isEmpty(chatMsg.data)) {
                    Intent intent2 = new Intent();
                    intent2.setClass(activity, chatMsg.isGroupMsg() ? GroupConversationActivity.class : ConversationActivity.class);
                    intent2.putExtra(IMChatBaseActivity.USER_TYPE, chatMsg.usertype);
                    intent2.putExtra("to_id", chatMsg.uid);
                    QsbkApp.getInstance();
                    intent2.putExtra("user_id", QsbkApp.currentUser.userId);
                    intent2.putExtra(IMChatBaseActivity.TO_NICK, chatMsg.isGroupMsg() ? chatMsg.gnick : chatMsg.getFromNick());
                    intent2.putExtra(IMChatBaseActivityEx.REMOVE_NOTIFICATION, true);
                    intent2.setFlags(ClientDefaults.MAX_MSG_SIZE);
                    activity.startActivity(intent2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void toPickImage(Activity activity, int i, int i2) {
        Intent prepareIntent = ImagesPickerActivity.prepareIntent(activity, i2);
        prepareIntent.putExtra("KEY_PICK_IAMGE", 4);
        activity.startActivityForResult(prepareIntent, i);
    }

    public String getUserSig() {
        return null;
    }

    public void setUserSig(String str) {
    }
}
