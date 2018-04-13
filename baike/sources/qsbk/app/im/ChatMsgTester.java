package qsbk.app.im;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.net.URLEncoder;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;

public class ChatMsgTester {
    public static void sendQiuYouXiuPushMsg() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("art_img", "http://pic.cafe.qiushibaike.com/FrOqvGrkcq3fKtVzO2zlR8-g0awB?imageView/2/w/500/q/90");
            jSONObject2.put("art_content", "哈哈哈哈哈哈");
            jSONObject2.put("user_nick", "游客二十");
            jSONObject2.put("action_desc", "喜欢了您的文章");
            jSONObject2.put("url", "/index.html?page=in-post&page-args=%7B%22cafe_id%22%3A1018139%2C%22id%22%3A214432%2C%22comment_id%22%3A3890496%2C%22cafe_name%22%3A%22%22%2C%22showCafe%22%3Atrue%2C%22comment_floor%22%3A7806%7D");
            jSONObject.put("jump_data", jSONObject2);
            jSONObject.put("jump", "cafe");
            jSONObject.put("d", "游客二十喜欢了您的文章");
        } catch (JSONException e) {
        }
        ChatMsg chatMsg = new ChatMsg(20, jSONObject.toString());
        chatMsg.usertype = 4;
        chatMsg.from = "22791001";
        chatMsg.fromnick = "糗友秀推送";
        UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onMessageReceived(chatMsg);
    }

    public static void mockRecvALotGroupMsg() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new bm());
    }

    public static void openKuaishouFollower(Context context) {
        try {
            Uri parse = Uri.parse("ks://users/follower/65815274");
            Intent intent = new Intent();
            intent.setData(parse);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    public static void openKuaishouProfile(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("user_id", "65815274");
            jSONObject.put("user_name", "哈哈");
            jSONObject.put("user_sex", "F");
            Uri parse = Uri.parse("ks://profile/65815274?user=" + URLEncoder.encode(jSONObject.toString(), "UTF-8"));
            Intent intent = new Intent();
            intent.setData(parse);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
