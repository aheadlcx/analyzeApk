package qsbk.app.push;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.model.CircleTopic;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.image.issue.Logger;

public class XiaoMiPushReceiver extends PushMessageReceiver {
    String TAG = XiaoMiPushReceiver.class.getSimpleName();
    private String mRegId;

    @SuppressLint({"SimpleDateFormat"})
    public static String getSimpleDate() {
        return new SimpleDateFormat("MM-dd hh:mm:ss").format(new Date());
    }

    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        Log.v(this.TAG, "onReceivePassThroughMessage is called. " + miPushMessage.toString());
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        Log.v(this.TAG, "onNotificationMessageClicked is called. " + miPushMessage.toString());
        try {
            QBPushMessage commPushMessage = new CommPushMessage(miPushMessage.getContent());
            JSONObject jSONObject = new JSONObject(commPushMessage.getCustomJson());
            String string = jSONObject.getString("t");
            if ("art".equals(string)) {
                gotoSingleArticle(context, jSONObject.getString("v"));
            } else if ("url".equals(string)) {
                gotoUrl(context, commPushMessage);
            } else if ("topic_list".equals(string)) {
                processTopicPushMessage(jSONObject, context);
            } else if ("msg".equals(string)) {
                processIMPushMessage(jSONObject, context);
            } else if (QYQShareInfo.TYPE_QSJX.equals(string)) {
                processQsjxMessage(jSONObject, context);
            } else {
                gotoMain(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoUrl(Context context, QBPushMessage qBPushMessage) {
        JSONObject jSONObject;
        JSONException e;
        Intent intent = new Intent();
        intent.setClass(context, PushMessageShow.class);
        String str = "糗事百科";
        String title = qBPushMessage.getTitle();
        if (TextUtils.isEmpty(title)) {
            title = str;
        }
        String str2 = "url";
        int i = 0;
        try {
            jSONObject = new JSONObject(qBPushMessage.getCustomJson());
            try {
                intent.putExtra("url", jSONObject.getString("v"));
                intent.putExtra("id", PushMessageShow.setOpenURL(jSONObject.getString("v")));
                if (!jSONObject.isNull("i")) {
                    i = jSONObject.getInt("i");
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                intent.putExtra("title", title);
                intent.putExtra("push", true);
                intent.putExtra(QsbkDatabase.LOGIN, "1".equals(jSONObject.optString("n")));
                intent.putExtra("msgid", i);
                intent.putExtra(PushMessageProcessor.PUSH_LABEL, str2);
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                context.startActivity(intent);
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            jSONObject = null;
            e = jSONException;
            e.printStackTrace();
            intent.putExtra("title", title);
            intent.putExtra("push", true);
            intent.putExtra(QsbkDatabase.LOGIN, "1".equals(jSONObject.optString("n")));
            intent.putExtra("msgid", i);
            intent.putExtra(PushMessageProcessor.PUSH_LABEL, str2);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
        intent.putExtra("title", title);
        intent.putExtra("push", true);
        intent.putExtra(QsbkDatabase.LOGIN, "1".equals(jSONObject.optString("n")));
        intent.putExtra("msgid", i);
        intent.putExtra(PushMessageProcessor.PUSH_LABEL, str2);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    private void gotoSingleArticle(Context context, String str) {
        Intent intent = new Intent();
        intent.setClass(context, SingleArticle.class);
        intent.putExtra("article_id", str);
        intent.putExtra("source", "only_article_id");
        intent.putExtra("from_push", true);
        intent.setFlags(402653184);
        context.startActivity(intent);
    }

    private void gotoSubscribe(Context context, String str) {
        preloadArticle(context, str);
    }

    public void processTopicPushMessage(JSONObject jSONObject, Context context) {
        Serializable circleTopic = new CircleTopic();
        circleTopic.id = jSONObject.optString("v");
        circleTopic.content = "";
        if (!TextUtils.isEmpty(circleTopic.id)) {
            Intent intent = new Intent(context, CircleTopicActivity.class);
            intent.putExtra("topic", circleTopic);
            intent.putExtra("pic_index", -1);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }

    public void processIMPushMessage(JSONObject jSONObject, Context context) {
        Logger.getInstance().debug(this.TAG, "processIMPushMessage", "收到服务器的推送：" + jSONObject);
        IMNotifyManager.instance().onPushNotify(jSONObject, context);
    }

    private void gotoMain(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        Log.v(this.TAG, "onNotificationMessageArrived is called. " + miPushMessage.toString());
    }

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        Log.v(this.TAG, "onCommandResult is called. " + miPushCommandMessage.toString());
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        Log.v(this.TAG, "onReceiveRegisterResult is called. " + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if (!"register".equals(command)) {
            miPushCommandMessage.getReason();
        } else if (miPushCommandMessage.getResultCode() == 0) {
            this.mRegId = str;
            PushMessageManager.notifyPushBindServer("start", this.mRegId, "mipush");
            LogUtil.d("mRegId:" + this.mRegId);
        }
    }

    private void preloadArticle(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            new SimpleHttpTask(Constants.ARTICLE_DETAIL + str, new l(this, context)).execute();
        }
    }

    public void processQsjxMessage(JSONObject jSONObject, Context context) {
        if (IMNotifyManager.isSilentMode(context) && PushMessageProcessor.isInForbidonPushRange()) {
            LogUtil.d("forbidden push");
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("v");
        if (optJSONObject != null) {
            SharePreferenceUtils.remove("qsjx_articles");
            SharePreferenceUtils.setSharePreferencesValue("qsjx_articles", optJSONObject.toString());
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUSHI_ID);
            intent.putExtra(MainActivity.SHOW_QSJX_ARTICLES, true);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }
}
