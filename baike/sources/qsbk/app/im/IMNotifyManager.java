package qsbk.app.im;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.SparseArray;
import com.baidu.mobstat.Config;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.GroupNoticeActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.QiushiNotificationListActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.live.ui.LivePullActivity;
import qsbk.app.model.GroupInfo;
import qsbk.app.push.NotificationIDs;
import qsbk.app.push.PushMessageManager;
import qsbk.app.push.PushMessageProcessor;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.JoinedGroupGetter;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.MiUiUtils;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;

public class IMNotifyManager implements NotificationIDs {
    public static final String INVIS_USER_CENTER = "invis_user_center";
    public static final String PREF_KEY_NEW_FANS_NOTIFY = "NewFansNofity";
    public static final String PREF_KEY_NEW_MSG_SHOW_DETAIL = "NewMsgMsgDetail";
    public static final String PREF_KEY_NEW_MSG_SILENT_MODE = "SilentMode";
    public static final String PREF_KEY_NEW_MSG_SOUND = "NewMsgNotifySound";
    public static final String PREF_KEY_NEW_MSG_VIBRATE = "NewMsgVibrate";
    public static final String PREF_NAME = "IMNotificationSet";
    public static final String QIUSHI_SMILE = "qiushi_smile";
    public static final String SHOW_GROUP_TEMP_NOTIFICATION = "group_temp_notification";
    public static final String SHOW_JOIN_GROUP_NOTIFICATION = "join_group_notification";
    public static final String SHOW_NEW_MSG_NOTIFICATION = "new_msg_notification";
    public static final String SHOW_QIUSHI_NOTIFICATION = "qiushi_notificaiton";
    public static final String SHOW_QIUYOU_CIRCLE_NOTIFICATION = "qiuyou_circle_notification";
    public static final String TOPIC = "circle_topic";
    private static IMNotifyManager a = null;
    public static boolean hideContent = false;
    private boolean b = false;
    private IMNotificationMerge c = new IMNotificationMerge(this);
    public String mIcon;
    public String mLastGNick;
    public String mLastMsg;
    public String mLastPrefix;
    public String mLastUser;
    public SparseArray<Integer> unReadMsg = new SparseArray();
    public SparseArray<Integer> userType = new SparseArray();

    public class IMNotificationMerge implements Runnable {
        final /* synthetic */ IMNotifyManager a;
        private Handler b = null;
        private long c = 0;
        private ChatMsg d;

        public IMNotificationMerge(IMNotifyManager iMNotifyManager) {
            this.a = iMNotifyManager;
        }

        private synchronized Handler a() {
            if (this.b == null) {
                this.b = new Handler(Looper.getMainLooper());
            }
            return this.b;
        }

        public synchronized void fire(ChatMsg chatMsg) {
            long j = 1000;
            synchronized (this) {
                long currentTimeMillis = System.currentTimeMillis();
                this.d = chatMsg;
                if (this.c == 0) {
                    Handler a = a();
                    a.removeCallbacks(this);
                    a.postDelayed(this, 1000);
                    this.c = currentTimeMillis;
                } else if (currentTimeMillis - this.c > 1000) {
                    run();
                } else {
                    Handler a2 = a();
                    a2.removeCallbacks(this);
                    currentTimeMillis = 1000 - (currentTimeMillis - this.c);
                    if (currentTimeMillis < 0) {
                        currentTimeMillis = 10;
                    }
                    if (currentTimeMillis <= 1000) {
                        j = currentTimeMillis;
                    }
                    a2.postDelayed(this, j);
                }
            }
        }

        public void run() {
            LogUtil.d("fire notification: " + System.currentTimeMillis());
            this.a.a(AppContext.getContext(), this.d);
            this.c = System.currentTimeMillis();
        }
    }

    public interface NotificationBuilt {
        void onBuilt(Notification notification);
    }

    private IMNotifyManager() {
    }

    public static synchronized IMNotifyManager instance() {
        IMNotifyManager iMNotifyManager;
        synchronized (IMNotifyManager.class) {
            if (a == null) {
                a = new IMNotifyManager();
            }
            iMNotifyManager = a;
        }
        return iMNotifyManager;
    }

    static Notification a(Context context, String str, String str2, PendingIntent pendingIntent, Bitmap bitmap, boolean z, boolean z2) {
        return a(context, str, str2, pendingIntent, bitmap, z, z2, null);
    }

    static Notification a(Context context, String str, String str2, PendingIntent pendingIntent, Bitmap bitmap, boolean z, boolean z2, String str3) {
        Builder largeIcon = new Builder(context).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(str2).setContentText(str).setSmallIcon(R.drawable.ic_im_small).setLargeIcon(bitmap);
        if (TextUtils.isEmpty(str3)) {
            str3 = str;
        }
        Notification build = largeIcon.setTicker(str3).setStyle(new BigTextStyle().bigText(str)).build();
        if (z) {
            build.defaults |= 1;
        }
        if (z2) {
            build.defaults |= 2;
        }
        return build;
    }

    public static boolean isNewMsgSound(Context context) {
        return a(context, PREF_KEY_NEW_MSG_SOUND, true);
    }

    private static boolean a(Context context, String str, boolean z) {
        return context.getSharedPreferences(PREF_NAME, 0).getBoolean(str, z);
    }

    private static void a(Context context, String str, boolean z, boolean z2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        if (!sharedPreferences.contains(str) || z2) {
            sharedPreferences.edit().putBoolean(str, z).apply();
        }
    }

    public static void canNewMsgSound(Context context, boolean z, boolean z2) {
        a(context, PREF_KEY_NEW_MSG_SOUND, z, z2);
    }

    public static boolean isNewMsgVibrate(Context context) {
        return a(context, PREF_KEY_NEW_MSG_VIBRATE, true);
    }

    public static void canNewMsgVibrate(Context context, boolean z, boolean z2) {
        a(context, PREF_KEY_NEW_MSG_VIBRATE, z, z2);
    }

    public static boolean isNewMsgShowDetail(Context context) {
        return a(context, PREF_KEY_NEW_MSG_SHOW_DETAIL, true);
    }

    public static boolean isShowQiushiNotification(Context context) {
        return a(context, SHOW_QIUSHI_NOTIFICATION, true);
    }

    public static boolean isShowQiuyouCircleNotificaiton(Context context) {
        return a(context, SHOW_QIUYOU_CIRCLE_NOTIFICATION, true);
    }

    public static boolean isShowJoinGroupNotificaiton(Context context) {
        return a(context, SHOW_JOIN_GROUP_NOTIFICATION, true);
    }

    public static boolean isShowGroupTempNotificaiton(Context context) {
        return a(context, SHOW_GROUP_TEMP_NOTIFICATION, true);
    }

    public static boolean isInvisUserCenter(Context context) {
        return a(context, INVIS_USER_CENTER, true);
    }

    public static boolean isShowNewMsgNotificaiton(Context context) {
        return a(context, SHOW_NEW_MSG_NOTIFICATION, true);
    }

    public static boolean isShowQiushiSmileNotification(Context context) {
        return a(context, QIUSHI_SMILE, true);
    }

    public static boolean isShowCircleTopicNotification(Context context) {
        return a(context, "circle_topic", true);
    }

    public static void canShowQiushiNotification(Context context, boolean z, boolean z2) {
        a(context, SHOW_QIUSHI_NOTIFICATION, z, z2);
    }

    public static void canShowQiuyouCircleNotification(Context context, boolean z, boolean z2) {
        a(context, SHOW_QIUYOU_CIRCLE_NOTIFICATION, z, z2);
    }

    public static void canShowJoinGroupNotification(Context context, boolean z, boolean z2) {
        a(context, SHOW_JOIN_GROUP_NOTIFICATION, z, z2);
    }

    public static void canShowQiushiSmile(Context context, boolean z, boolean z2) {
        a(context, QIUSHI_SMILE, z, z2);
    }

    public static void canShowCircleTopic(Context context, boolean z, boolean z2) {
        a(context, "circle_topic", z, z2);
    }

    public static void canShowTemNoteNotification(Context context, boolean z, boolean z2) {
        a(context, SHOW_GROUP_TEMP_NOTIFICATION, z, z2);
    }

    public static void canInvisUserCenter(Context context, boolean z, boolean z2) {
        a(context, INVIS_USER_CENTER, z, z2);
    }

    public static void canShowNewMsgNotification(Context context, boolean z, boolean z2) {
        a(context, SHOW_NEW_MSG_NOTIFICATION, z, z2);
    }

    public static void canNewMsgShowDetail(Context context, boolean z, boolean z2) {
        a(context, PREF_KEY_NEW_MSG_SHOW_DETAIL, z, z2);
    }

    public static boolean isSilentMode(Context context) {
        return a(context, PREF_KEY_NEW_MSG_SILENT_MODE, false);
    }

    public static void canSilentMode(Context context, boolean z, boolean z2) {
        a(context, PREF_KEY_NEW_MSG_SILENT_MODE, z, z2);
    }

    public static boolean isNewFansNotify(Context context) {
        return a(context, PREF_KEY_NEW_FANS_NOTIFY, true);
    }

    public static boolean isQiushiSmileNotify(Context context) {
        return a(context, QIUSHI_SMILE, true);
    }

    public static void canNewFansNotify(Context context, boolean z, boolean z2) {
        a(context, PREF_KEY_NEW_FANS_NOTIFY, z, z2);
    }

    public static void parseAndSetIMNotification(String str) {
        boolean z = true;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, 1) == 0) {
                    boolean z2;
                    canNewMsgSound(AppContext.getContext(), jSONObject.optInt("pushImSound", 1) == 1, true);
                    Context context = AppContext.getContext();
                    if (jSONObject.optInt("pushImDetail", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canNewMsgShowDetail(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushImVibrate", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canNewMsgVibrate(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushAntiDisturb", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canSilentMode(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushNewFan", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canNewFansNotify(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushQiushi", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canShowQiushiNotification(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushCircle", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canShowQiuyouCircleNotification(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushTribeNotice", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canShowJoinGroupNotification(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushTribeTmp", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canShowTemNoteNotification(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("pushSingle", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canShowNewMsgNotification(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("invisUserCenter", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canInvisUserCenter(context, z2, true);
                    context = AppContext.getContext();
                    if (jSONObject.optInt("qiushiSmile", 1) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    canShowQiushiSmile(context, z2, true);
                    Context context2 = AppContext.getContext();
                    if (jSONObject.optInt("topic", 1) != 1) {
                        z = false;
                    }
                    canShowCircleTopic(context2, z, true);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveSettingToCloud() {
        int i = 1;
        LogUtil.e("保存将配置到云端");
        if (QsbkApp.currentUser != null) {
            int i2;
            Map hashMap = new HashMap();
            hashMap.put("pushImDetail", Integer.valueOf(isNewMsgShowDetail(AppContext.getContext()) ? 1 : 0));
            String str = "pushAntiDisturb";
            if (isSilentMode(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushImSound";
            if (isNewMsgSound(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushImVibrate";
            if (isNewMsgVibrate(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushNewFan";
            if (isNewFansNotify(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushArticle";
            if (PushMessageManager.getReceiveMsgFromLocal()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushQiushi";
            if (isShowQiushiNotification(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushCircle";
            if (isShowQiuyouCircleNotificaiton(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushTribeNotice";
            if (isShowJoinGroupNotificaiton(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushTribeTmp";
            if (isShowGroupTempNotificaiton(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "pushSingle";
            if (isShowNewMsgNotificaiton(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "invisUserCenter";
            if (isInvisUserCenter(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            str = "qiushiSmile";
            if (isQiushiSmileNotify(AppContext.getContext())) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            hashMap.put(str, Integer.valueOf(i2));
            String str2 = "topic";
            if (!isShowCircleTopicNotification(AppContext.getContext())) {
                i = 0;
            }
            hashMap.put(str2, Integer.valueOf(i));
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.NOTIFY_SETTING_SET, new hu());
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public static void getSettingFromCloud() {
        if (QsbkApp.currentUser != null) {
            new SimpleHttpTask(String.format(Constants.NOTIFY_SETTING_GET, new Object[]{QsbkApp.currentUser.userId}), new hv()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public static void clear() {
        a = null;
    }

    private String a() {
        if (QsbkApp.currentUser != null) {
            return QsbkApp.currentUser.userId;
        }
        return null;
    }

    public void onUserLogout() {
        cleanNotification();
    }

    private Bitmap a(Bitmap bitmap, int i) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, i, i, true);
    }

    private synchronized void a(Context context, NotificationBuilt notificationBuilt, ChatMsg chatMsg) {
        String str = "";
        String str2 = "";
        String str3 = "";
        int size = this.unReadMsg.size();
        if (size != 0) {
            int keyAt;
            String format;
            PendingIntent pendingIntent;
            PendingIntent pendingIntent2 = null;
            String optString;
            long optLong;
            if (size == 1) {
                String str4;
                PendingIntent activity;
                keyAt = this.unReadMsg.keyAt(0);
                int intValue = ((Integer) this.unReadMsg.get(keyAt)).intValue();
                if (this.mLastGNick == null) {
                    str4 = this.mLastUser;
                } else {
                    str4 = this.mLastGNick;
                }
                str2 = RemarkManager.getRemark(String.valueOf(keyAt));
                if (TextUtils.isEmpty(str2)) {
                    str2 = str4;
                }
                if (intValue == 1) {
                    format = String.format("%s 发来1条消息", new Object[]{str2});
                    if (!TextUtils.isEmpty(this.mLastMsg)) {
                        str = this.mLastMsg;
                    }
                } else {
                    format = String.format("%s 发来%d条消息", new Object[]{str2, Integer.valueOf(intValue)});
                    str = this.mLastMsg;
                }
                str3 = str2 + Config.TRACE_TODAY_VISIT_SPLIT + this.mLastMsg;
                Logger.getInstance().debug(IMNotifyManager.class.getSimpleName(), "buildNotification", String.format("收到推送[userCount:%s, fromId:%s, singleSize:%s, mLastMsg:%s]", new Object[]{Integer.valueOf(size), Integer.valueOf(keyAt), Integer.valueOf(intValue), this.mLastMsg, str}));
                Intent intent = new Intent();
                if (chatMsg.usertype == 6) {
                    intent.setClass(context, MainActivity.class);
                    intent.putExtra(MainActivity.GO_FAMILY_MESSAGE, true);
                    intent.putExtra(MainActivity.CLEAR_FAMILY_MESSAGE, true);
                    intent.putExtra(MainActivity.CLEAR_MESSAGE_ID, chatMsg.from);
                    if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                        activity = PendingIntent.getActivity(AppContext.getContext(), NotificationIDs.NOTIFICATION_ID, intent, 1207959552);
                    } else {
                        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                        context.startActivity(intent);
                    }
                } else if (chatMsg.type == 26) {
                    try {
                        JSONObject jSONObject = new JSONObject(chatMsg.data);
                        optString = jSONObject.optString("live_id");
                        optLong = jSONObject.optLong("origin");
                        intent.setClass(context, LivePullActivity.class);
                        intent.putExtra("liveUserId", optString);
                        intent.putExtra("liveUserSource", optLong);
                        intent.putExtra(LivePullLauncher.STSOURCE, "notification");
                        if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                            activity = PendingIntent.getActivity(AppContext.getContext(), NotificationIDs.NOTIFICATION_ID, intent, 1207959552);
                        } else {
                            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                            context.startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        activity = pendingIntent2;
                    }
                } else {
                    intent.setClass(AppContext.getContext(), this.b ? GroupConversationActivity.class : ConversationActivity.class);
                    intent.putExtra(IMChatBaseActivity.USER_TYPE, (Serializable) this.userType.get(keyAt));
                    intent.putExtra("to_id", String.valueOf(keyAt));
                    intent.putExtra("user_id", a());
                    intent.putExtra(IMChatBaseActivity.TO_NICK, this.b ? this.mLastGNick : this.mLastUser);
                    intent.putExtra(IMChatBaseActivityEx.REMOVE_NOTIFICATION, true);
                    intent.setFlags(67108864);
                    if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                        activity = PendingIntent.getActivity(AppContext.getContext(), NotificationIDs.NOTIFICATION_ID, intent, 1207959552);
                    } else {
                        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                        context.startActivity(intent);
                    }
                }
                pendingIntent = activity;
            } else {
                keyAt = 0;
                for (int i = 0; i < size; i++) {
                    keyAt += ((Integer) this.unReadMsg.get(this.unReadMsg.keyAt(i))).intValue();
                }
                format = "糗事百科";
                str = String.format("%d个糗友发来%d条消息", new Object[]{Integer.valueOf(size), Integer.valueOf(keyAt)});
                Logger.getInstance().debug(IMNotifyManager.class.getSimpleName(), "buildNotification", String.format("msgCount:%s, content:%s", new Object[]{Integer.valueOf(keyAt), str}));
                Intent intent2 = new Intent();
                if (chatMsg.type == 26) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(chatMsg.data);
                        optString = jSONObject2.optString("live_id");
                        optLong = jSONObject2.optLong("origin");
                        intent2.setClass(context, LivePullActivity.class);
                        intent2.putExtra("liveUserId", optString);
                        intent2.putExtra("liveUserSource", optLong);
                        intent2.putExtra(LivePullLauncher.STSOURCE, "notification");
                        intent2.putExtra("extraString", a(chatMsg));
                        if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                            pendingIntent2 = PendingIntent.getActivity(AppContext.getContext(), NotificationIDs.NOTIFICATION_ID, intent2, 1207959552);
                            pendingIntent = pendingIntent2;
                        } else {
                            intent2.addFlags(ClientDefaults.MAX_MSG_SIZE);
                            context.startActivity(intent2);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    intent2.setClass(AppContext.getContext(), MainActivity.class);
                    intent2.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_MESSAGE_ID);
                    intent2.putExtra("user_id", a());
                    intent2.setFlags(67108864);
                    if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                        pendingIntent = PendingIntent.getActivity(AppContext.getContext(), NotificationIDs.NOTIFICATION_ID, intent2, 1207959552);
                    } else {
                        intent2.addFlags(ClientDefaults.MAX_MSG_SIZE);
                        context.startActivity(intent2);
                    }
                }
            }
            keyAt = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
            if (size == 1) {
                this.unReadMsg.keyAt(0);
                String str5 = this.mIcon;
                StringBuilder stringBuilder = new StringBuilder();
                if (this.mLastPrefix == null) {
                    str2 = "";
                } else {
                    str2 = this.mLastPrefix;
                }
                Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str5), context.getApplicationContext()).subscribe(new hw(this, context, keyAt, notificationBuilt, stringBuilder.append(str2).append(str).toString(), format, pendingIntent, str3), CallerThreadExecutor.getInstance());
            } else {
                notificationBuilt.onBuilt(a(context, str, format, pendingIntent, a(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher), keyAt), isNewMsgSound(context), isNewMsgVibrate(context)));
            }
        }
        return;
    }

    public void cleanNotification() {
        this.unReadMsg.clear();
        ((NotificationManager) AppContext.getContext().getSystemService("notification")).cancel(NotificationIDs.NOTIFICATION_ID);
    }

    private String a(ChatMsg chatMsg) {
        if (chatMsg != null) {
            return chatMsg.encodeToJsonObject().toString();
        }
        return "";
    }

    public void onPushNotify(JSONObject jSONObject, Context context) {
        Logger.getInstance().debug(IMNotifyManager.class.getSimpleName(), "onPushNotify", "notify from server jimwang ." + jSONObject);
        if (jSONObject != null && jSONObject.optString("to", "").equals(a())) {
            new IMPreConnector().preConnect(null);
        }
    }

    public void onChatMsgNotify(ChatMsg chatMsg, boolean z) {
        int parseInt;
        try {
            this.b = chatMsg.isGroupMsg();
            parseInt = chatMsg.isGroupMsg() ? Integer.parseInt(chatMsg.gid) : Integer.parseInt(chatMsg.from);
        } catch (Exception e) {
            parseInt = 0;
        }
        if (parseInt != 0 && chatMsg.isContentMsg() && !TextUtils.isEmpty(chatMsg.fromnick) && chatMsg.type != 8) {
            String str;
            if (hideContent) {
                str = null;
            } else {
                str = chatMsg.getMsgTips();
                this.userType.put(parseInt, Integer.valueOf(chatMsg.usertype));
                if (chatMsg.type == 10) {
                    a(chatMsg, str);
                    return;
                } else if (chatMsg.type == 20) {
                    c(chatMsg, str);
                    return;
                } else if (chatMsg.type == 202) {
                    b(chatMsg, str);
                    return;
                }
            }
            this.mLastMsg = str;
            this.mLastPrefix = null;
            if (chatMsg.isGroupMsg()) {
                this.mLastGNick = chatMsg.gnick;
                if (z) {
                    this.mLastPrefix = "[有人@我]";
                }
            } else {
                this.mLastGNick = null;
            }
            this.mLastUser = chatMsg.getFromNick();
            if (chatMsg.isGroupMsg()) {
                GroupInfo joinedGroupFromLocal = JoinedGroupGetter.getJoinedGroupFromLocal(String.valueOf(parseInt));
                if (joinedGroupFromLocal != null) {
                    this.mIcon = joinedGroupFromLocal.icon;
                }
            } else {
                this.mIcon = QsbkApp.absoluteUrlOfMediumUserIcon(chatMsg.getFromIcon(), String.valueOf(parseInt));
            }
            this.unReadMsg.put(parseInt, Integer.valueOf(((Integer) this.unReadMsg.get(parseInt, Integer.valueOf(0))).intValue() + 1));
            this.c.fire(chatMsg);
        }
    }

    private void a(ChatMsg chatMsg, String str) {
        Context context = AppContext.getContext();
        if (!(isSilentMode(context) && PushMessageProcessor.isInForbidonPushRange()) && isShowNewMsgNotificaiton(context)) {
            String str2;
            String str3;
            String remark = RemarkManager.getRemark(chatMsg.from);
            if (TextUtils.isEmpty(remark)) {
                str2 = chatMsg.getFromNick() + " 发来1条消息";
                str3 = chatMsg.getFromNick() + Config.TRACE_TODAY_VISIT_SPLIT + str;
            } else {
                str2 = remark + " 发来1条消息";
                str3 = remark + Config.TRACE_TODAY_VISIT_SPLIT + str;
            }
            Intent intent = new Intent(context, ConversationActivity.class);
            QsbkApp.getInstance();
            intent.putExtra("user_id", QsbkApp.currentUser.userId);
            intent.putExtra("to_id", chatMsg.uid);
            intent.putExtra(IMChatBaseActivity.TO_ICON, chatMsg.getFromIcon());
            intent.putExtra(IMChatBaseActivity.TO_NICK, chatMsg.getFromNick());
            intent.putExtra(IMChatBaseActivity.USER_TYPE, 1);
            if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                PendingIntent activity = PendingIntent.getActivity(context, NotificationIDs.NOTIFICATION_ID_OFFICIAL, intent, 1207959552);
                int i = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
                Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(chatMsg.getFromIcon(), chatMsg.uid);
                if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                    a(context, activity, str2, str, a(BitmapFactory.decodeResource(context.getResources(), UIHelper.getDefaultAvatar()), i), str3);
                    return;
                }
                Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(absoluteUrlOfMediumUserIcon), context.getApplicationContext()).subscribe(new hx(this, context, i, activity, str2, str, str3), CallerThreadExecutor.getInstance());
                return;
            }
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }

    private void a(Context context, PendingIntent pendingIntent, String str, String str2, Bitmap bitmap, String str3) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        }
        Notification build = new Builder(context).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.ic_official_push).setLargeIcon(bitmap).setTicker(str3).setStyle(new BigTextStyle().bigText(str2)).build();
        if (isNewMsgSound(context)) {
            build.defaults |= 1;
        }
        if (isNewMsgVibrate(context)) {
            build.defaults |= 2;
        }
        ((NotificationManager) context.getSystemService("notification")).notify(NotificationIDs.NOTIFICATION_ID_OFFICIAL, build);
    }

    private void a(Context context, ChatMsg chatMsg) {
        if (!(isSilentMode(context) && PushMessageProcessor.isInForbidonPushRange()) && isShowNewMsgNotificaiton(context)) {
            a(context, new hy(this, context), chatMsg);
        }
    }

    private void b(ChatMsg chatMsg, String str) {
        Context context = AppContext.getContext();
        if (!(isSilentMode(context) && PushMessageProcessor.isInForbidonPushRange()) && isShowJoinGroupNotificaiton(context)) {
            String str2;
            String str3;
            Object remark = RemarkManager.getRemark(chatMsg.from);
            if (TextUtils.isEmpty(remark)) {
                str2 = chatMsg.getFromNick() + " 有1条消息";
                str3 = str;
            } else {
                str2 = remark + " 有1条消息";
                str3 = str;
            }
            Intent intent = new Intent(context, GroupNoticeActivity.class);
            if (a(context) || !MiUiUtils.isMiUiSystemPushEnable()) {
                PendingIntent activity = PendingIntent.getActivity(context, NotificationIDs.NOTIFICATION_ID_GROUP_NOTICE, intent, 1207959552);
                int i = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
                remark = QsbkApp.absoluteUrlOfMediumUserIcon(chatMsg.getFromIcon(), chatMsg.uid);
                if (TextUtils.isEmpty(remark)) {
                    b(context, activity, str2, str, a(BitmapFactory.decodeResource(context.getResources(), UIHelper.getDefaultAvatar()), i), str3);
                    return;
                }
                Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(remark), context.getApplicationContext()).subscribe(new hz(this, context, i, activity, str2, str, str3), CallerThreadExecutor.getInstance());
                return;
            }
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }

    private void b(Context context, PendingIntent pendingIntent, String str, String str2, Bitmap bitmap, String str3) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        }
        Notification build = new Builder(context).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.ic_im_small).setLargeIcon(bitmap).setTicker(str3).setStyle(new BigTextStyle().bigText(str2)).build();
        if (isNewMsgSound(context)) {
            build.defaults |= 1;
        }
        if (isNewMsgVibrate(context)) {
            build.defaults |= 2;
        }
        ((NotificationManager) context.getSystemService("notification")).notify(NotificationIDs.NOTIFICATION_ID_GROUP_NOTICE, build);
    }

    private void c(ChatMsg chatMsg, String str) {
        Context context = AppContext.getContext();
        if (!isSilentMode(context) || !PushMessageProcessor.isInForbidonPushRange()) {
            if (chatMsg.from.trim().equals(QiushiNotificationCountManager.QIUSHI_PUSH_UID) && !isShowQiushiNotification(context)) {
                return;
            }
            if (!chatMsg.from.trim().equals(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID) || isShowQiuyouCircleNotificaiton(context)) {
                JSONObject jSONObject;
                String str2;
                String str3;
                Intent gotoQiuyouquanIntent;
                int i = 0;
                if (chatMsg.from.trim().equals(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID)) {
                    i = QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getUnreadCount();
                    try {
                        jSONObject = new JSONObject(chatMsg.data);
                        if (jSONObject != null) {
                            jSONObject = jSONObject.optJSONObject("jump_data");
                            if (jSONObject != null) {
                                int optInt = jSONObject.optInt("m_type", -1);
                                if (optInt == 101 || optInt == 102 || optInt == 103 || optInt == 4 || optInt == 105) {
                                    return;
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (chatMsg.from.trim().equals(QiushiNotificationCountManager.QIUSHI_PUSH_UID)) {
                    i = QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getUnreadCount();
                    try {
                        jSONObject = new JSONObject(chatMsg.data).optJSONObject("jump_data");
                        str2 = "";
                        if (jSONObject != null) {
                            CharSequence optString = jSONObject.optString("m_type");
                            if (!TextUtils.isEmpty(optString) && (Type.QIUSHI_SMILE.equals(optString) || Type.QIUSHI_COMMENT_LIKE.equals(optString) || "up".equals(optString) || Type.QIUSHI_COMMENT_LIKE_TOTAL.equals(optString) || Type.QIUSHI_AT_USERS.equals(optString))) {
                                return;
                            }
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                int max = Math.max(1, i);
                Object remark = RemarkManager.getRemark(chatMsg.from);
                if (TextUtils.isEmpty(remark)) {
                    str2 = String.format("%s 发来%s条消息", new Object[]{chatMsg.getFromNick(), Integer.valueOf(max)});
                    str3 = chatMsg.getFromNick() + Config.TRACE_TODAY_VISIT_SPLIT + str;
                } else {
                    str2 = String.format("%s 发来%s条消息", new Object[]{remark, Integer.valueOf(max)});
                    str3 = remark + Config.TRACE_TODAY_VISIT_SPLIT + str;
                }
                if (QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg.from)) {
                    gotoQiuyouquanIntent = QiushiNotificationListActivity.gotoQiuyouquanIntent(context, max);
                } else {
                    try {
                        JSONObject optJSONObject = new JSONObject(chatMsg.data).optJSONObject("jump_data");
                        CharSequence charSequence = "";
                        if (optJSONObject != null) {
                            charSequence = optJSONObject.optString("m_type");
                        }
                        if (TextUtils.isEmpty(charSequence) || !"promote".equals(charSequence)) {
                            gotoQiuyouquanIntent = QiushiNotificationListActivity.gotoQiushiIntent(context, max);
                        } else {
                            gotoQiuyouquanIntent = QiushiNotificationListActivity.gotoQiushiIntent(context, max, true);
                        }
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        gotoQiuyouquanIntent = null;
                    }
                }
                if (gotoQiuyouquanIntent != null && MiUiUtils.isMiUiSystemPushEnable()) {
                    gotoQiuyouquanIntent.setFlags(67108864);
                    if (!a(context)) {
                        gotoQiuyouquanIntent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                        context.startActivity(gotoQiuyouquanIntent);
                        return;
                    }
                }
                PendingIntent activity = PendingIntent.getActivity(context, NotificationIDs.NOTIFICATION_ID_QIUSHI_PUSH, gotoQiuyouquanIntent, 1207959552);
                int i2 = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
                remark = QsbkApp.absoluteUrlOfMediumUserIcon(chatMsg.getFromIcon(), chatMsg.uid);
                if (TextUtils.isEmpty(remark)) {
                    c(context, activity, str2, str, a(BitmapFactory.decodeResource(context.getResources(), UIHelper.getDefaultAvatar()), i2), str3);
                    return;
                }
                Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(remark), context.getApplicationContext()).subscribe(new ia(this, context, i2, activity, str2, str, str3), CallerThreadExecutor.getInstance());
            }
        }
    }

    private void c(Context context, PendingIntent pendingIntent, String str, String str2, Bitmap bitmap, String str3) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        }
        Notification build = new Builder(context).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.notification_icon).setLargeIcon(bitmap).setTicker(str3).setStyle(new BigTextStyle().bigText(str2)).build();
        if (isNewMsgSound(context)) {
            build.defaults |= 1;
        }
        if (isNewMsgVibrate(context)) {
            build.defaults |= 2;
        }
        ((NotificationManager) context.getSystemService("notification")).notify(NotificationIDs.NOTIFICATION_ID_QIUSHI_PUSH, build);
    }

    private boolean a(Context context) {
        if (context != null) {
            for (RunningTaskInfo runningTaskInfo : ((ActivityManager) context.getSystemService("activity")).getRunningTasks(100)) {
                if (TextUtils.equals("qsbk.app", runningTaskInfo.topActivity.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
