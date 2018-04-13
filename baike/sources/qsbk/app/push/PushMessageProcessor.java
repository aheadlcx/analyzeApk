package qsbk.app.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.qiushibaike.statsdk.StatSDK;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.QiuYouActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.RssArticle.Type;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.MiUiUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.Logger;

public class PushMessageProcessor implements NotificationIDs {
    public static final String PUSH_LABEL = "push_label";
    public static final String TAG = PushMessageProcessor.class.getSimpleName();

    public static boolean isInForbidonPushRange() {
        int parseInt = Integer.parseInt(new SimpleDateFormat("HH:mm:ss").format(new Date()).substring(0, 2));
        if (parseInt >= 23 || parseInt < 8) {
            return true;
        }
        return false;
    }

    public static void notification(Intent intent, Context context, String str, String str2, int i, Bitmap bitmap) {
        int i2;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        PendingIntent activity = PendingIntent.getActivity(context, i, intent, 134217728);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        }
        if (i == 100) {
            i2 = R.drawable.push_relationship;
        } else {
            i2 = R.drawable.notification_icon;
        }
        Notification build = new Builder(context).setAutoCancel(true).setContentIntent(activity).setContentTitle(str).setContentText(str2).setSmallIcon(i2).setLargeIcon(bitmap).setTicker(str2).setDefaults(1).setStyle(new BigTextStyle().bigText(str2)).build();
        if (IMNotifyManager.isNewMsgSound(context)) {
            build.defaults |= 1;
        }
        if (IMNotifyManager.isNewMsgVibrate(context)) {
            build.defaults |= 2;
        }
        notificationManager.notify(i, build);
    }

    private void a(Intent intent, Context context) {
        intent.setClass(context, MainActivity.class);
        intent.addFlags(67108864);
    }

    private void a(QBPushMessage qBPushMessage, Context context) {
        int i = 0;
        int i2 = -1;
        if (!(IMNotifyManager.isSilentMode(context) && isInForbidonPushRange()) && PushMessageManager.getReceiveMsgFromLocal()) {
            String str = "糗事百科";
            String str2 = "糗百有新内容,赶紧抢先看哦";
            Object obj = "";
            try {
                String str3;
                String title = qBPushMessage.getTitle();
                if (TextUtils.isEmpty(title)) {
                    title = str;
                }
                CharSequence customJson = qBPushMessage.getCustomJson();
                if (!TextUtils.isEmpty(customJson)) {
                    obj = customJson;
                }
                str = "您有来自糗事百科的1条消息";
                String description = qBPushMessage.getDescription();
                if (TextUtils.isEmpty(description)) {
                    description = str;
                }
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                Intent intent = new Intent();
                if (TextUtils.isEmpty(obj)) {
                    a(intent, context);
                    str3 = "main";
                    i = -1;
                } else {
                    JSONObject jSONObject = new JSONObject(obj);
                    if (!jSONObject.isNull("i")) {
                        i2 = jSONObject.getInt("i");
                    }
                    str3 = jSONObject.getString("t");
                    if ("list".equals(str3)) {
                        a(intent, context);
                    } else if ("url".equals(str3)) {
                        LogUtil.d("recv url message");
                        intent.setClass(context, PushMessageShow.class);
                        intent.putExtra("title", title);
                        intent.putExtra("push", true);
                        intent.putExtra("url", jSONObject.getString("v"));
                        intent.putExtra(QsbkDatabase.LOGIN, "1".equals(jSONObject.optString("n")));
                        intent.putExtra("id", PushMessageShow.setOpenURL(jSONObject.getString("v")));
                        i = 2;
                    } else if ("art".equals(str3)) {
                        LogUtil.d("art type");
                        if (MiUiUtils.isMiUiSystemPushEnable()) {
                            LogUtil.d("miui and return");
                            return;
                        }
                        LogUtil.e("test " + jSONObject.toString());
                        if (jSONObject.isNull("j") || !Type.SUB.equals(jSONObject.getString("j"))) {
                            intent.setClass(context, SingleArticle.class);
                            intent.putExtra("article_id", jSONObject.getString("v"));
                            intent.putExtra("source", "only_article_id");
                            intent.putExtra("from_push", true);
                            i = 1;
                        } else {
                            a(context, jSONObject.getString("v"), i2, str3, title, description, 1);
                            return;
                        }
                    } else {
                        a(intent, context);
                        str3 = "main";
                    }
                }
                intent.putExtra("msgid", i2);
                intent.putExtra(PUSH_LABEL, str3);
                if (intent.getComponent() != null && SingleArticle.class.getName().equals(intent.getComponent().getClassName()) && isInForbidonPushRange()) {
                    LogUtil.d("push cancel");
                    return;
                } else {
                    notification(intent, context, title, description, i, null);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        LogUtil.d("forbidden push");
    }

    public void processMessage(QBPushMessage qBPushMessage, Context context) {
        try {
            JSONObject jSONObject = new JSONObject(qBPushMessage.getCustomJson());
            LogUtil.d("push msg:" + qBPushMessage.getCustomJson());
            String optString = jSONObject.optString("t", "");
            if (optString.equals("msg")) {
                LogUtil.d("im push message");
                processIMPushMessage(jSONObject, context);
            } else if ("list".equals(optString) || optString.equals("url") || optString.equals("art")) {
                LogUtil.d("qiubai push message");
                a(qBPushMessage, context);
            } else if (optString.equals("rel_follow") || optString.equals("rel_friend")) {
                processRelPushMessage(jSONObject, context);
            } else if ("topic_list".equals(optString)) {
                processTopicPushMessage(jSONObject, context);
            } else if ("checkin".equals(optString)) {
                processSignPushMessage(qBPushMessage, context);
            } else if (QYQShareInfo.TYPE_QSJX.equals(optString)) {
                processQsjxMessage(jSONObject, context);
            }
            StatService.onEvent(context, "recv_push", optString);
            StatSDK.onEvent(context, "recv_push", optString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processSignPushMessage(QBPushMessage qBPushMessage, Context context) {
        if (!(IMNotifyManager.isSilentMode(context) && isInForbidonPushRange()) && IMNotifyManager.isShowCircleTopicNotification(context)) {
            String str = "糗事百科";
            String str2 = "今天还未签到,您可以去签到了哦!";
            str2 = "";
            try {
                String description;
                String title = qBPushMessage.getTitle();
                if (TextUtils.isEmpty(title)) {
                    title = str;
                }
                if (TextUtils.isEmpty(qBPushMessage.getCustomJson())) {
                    str = "今天还未签到,您可以去签到了哦!";
                    description = qBPushMessage.getDescription();
                } else {
                    str = "今天还未签到,您可以去签到了哦!";
                    description = qBPushMessage.getDescription();
                }
                if (TextUtils.isEmpty(description)) {
                    description = str;
                }
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUYOUCIRCLE_ID);
                intent.putExtra("fromSignUp", true);
                intent.addFlags(67108864);
                intent.putExtra("msgid", -1);
                intent.putExtra(PUSH_LABEL, "checkin");
                notification(intent, context, title, description, -1, null);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        LogUtil.d("forbidden push");
    }

    public void processTopicPushMessage(JSONObject jSONObject, Context context) {
        if (!(IMNotifyManager.isSilentMode(context) && isInForbidonPushRange()) && IMNotifyManager.isShowCircleTopicNotification(context)) {
            Serializable circleTopic = new CircleTopic();
            circleTopic.id = jSONObject.optString("v");
            circleTopic.content = "";
            if (!TextUtils.isEmpty(circleTopic.id)) {
                String optString = jSONObject.optString("title");
                String optString2 = jSONObject.optString("description");
                if (TextUtils.isEmpty(optString)) {
                    optString = "精彩话题";
                }
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = "精彩话题，不容错过";
                }
                Intent intent = new Intent(context, CircleTopicActivity.class);
                intent.putExtra("topic", circleTopic);
                intent.putExtra("pic_index", -1);
                intent.addFlags(67108864);
                notification(intent, context, optString, optString2, 1, null);
                return;
            }
            return;
        }
        LogUtil.d("forbidden push");
    }

    public void processIMPushMessage(JSONObject jSONObject, Context context) {
        Logger.getInstance().debug(TAG, "processIMPushMessage", "收到服务器的推送：" + jSONObject);
        IMNotifyManager.instance().onPushNotify(jSONObject, context);
    }

    private Bitmap a(Bitmap bitmap, int i) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, i, i, true);
    }

    public void processRelPushMessage(JSONObject jSONObject, Context context) {
        if (QsbkApp.currentUser != null && IMNotifyManager.isNewFansNotify(context)) {
            Intent intent = new Intent();
            String optString = jSONObject.optString("t", "");
            if ("rel_follow".equalsIgnoreCase(optString)) {
                intent.setClass(context, QiuYouActivity.class);
                intent.putExtra("has_new_fans", true);
            } else if ("rel_friend".equalsIgnoreCase(optString)) {
                intent.setClass(context, ConversationActivity.class);
                QsbkApp.getInstance();
                intent.putExtra("user_id", QsbkApp.currentUser.userId);
                intent.putExtra("to_id", jSONObject.optString("frm", ""));
                intent.putExtra(IMChatBaseActivity.TO_ICON, jSONObject.optString("icon", ""));
                intent.putExtra(IMChatBaseActivity.TO_NICK, jSONObject.optString(ParamKey.NICK, ""));
                intent.putExtra(ConversationActivity.RELATIONSHIP, Relationship.FRIEND);
            }
            LogUtil.d(jSONObject.toString());
            int i = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
            String optString2 = jSONObject.optString("cnt");
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(jSONObject.optString("icon"), jSONObject.optString("frm") + "");
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                Context context2 = context;
                notification(intent, context2, "我的新粉丝", optString2, 100, a(BitmapFactory.decodeResource(context.getResources(), UIHelper.getDefaultAvatar()), i));
                return;
            }
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(absoluteUrlOfMediumUserIcon), context.getApplicationContext()).subscribe(new g(this, context, i, intent, optString2), CallerThreadExecutor.getInstance());
        }
    }

    public void processQsjxMessage(JSONObject jSONObject, Context context) {
        if (IMNotifyManager.isSilentMode(context) && isInForbidonPushRange()) {
            LogUtil.d("forbidden push");
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("v");
        if (optJSONObject != null) {
            SharePreferenceUtils.remove("qsjx_articles");
            SharePreferenceUtils.setSharePreferencesValue("qsjx_articles", optJSONObject.toString());
            String str = "糗事精选";
            String optString = optJSONObject.optString("title");
            if (TextUtils.isEmpty(optString)) {
                optString = "精选糗事，不容错过";
            }
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUSHI_ID);
            intent.putExtra(MainActivity.SHOW_QSJX_ARTICLES, true);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            notification(intent, context, str, optString, 1, null);
        }
    }

    private void a(Context context, String str, int i, String str2, String str3, String str4, int i2) {
        if (!TextUtils.isEmpty(str)) {
            new SimpleHttpTask(Constants.ARTICLE_DETAIL + str, new h(this, context, i, str2, str3, str4, i2)).execute();
        }
    }
}
