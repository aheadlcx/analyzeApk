package qsbk.app.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.ActionBarUserSettingNavi;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.EventWindowActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.QiushiNotificationListActivity;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.http.HttpTask;
import qsbk.app.im.GameWebViewActivity;
import qsbk.app.live.ui.LivePullActivity;
import qsbk.app.utils.SharePreferenceUtils;

public class EventWindow implements Serializable {
    public static final String JUMP_CIRCLE_DETAIL = "circle_article_detail";
    public static final String JUMP_CIRCLE_FOLLOW = "circle_follow";
    public static final String JUMP_CIRCLE_NEARBY = "circle_nearby";
    public static final String JUMP_CIRCLE_POST = "circle_post";
    public static final String JUMP_CIRCLE_TOPIC = "circle_topic";
    public static final String JUMP_CIRCLE_TOPIC_DETAIL = "circle_topic_detail";
    public static final String JUMP_CIRCLE_VIDEO = "circle_video";
    public static final String JUMP_CIRLCE_NOTICE = "circle_notice";
    public static final String JUMP_DUOBAO = "duobao";
    public static final String JUMP_GAME = "game";
    public static final String JUMP_IM = "im";
    public static final String JUMP_LIVE_ALL = "live_all";
    public static final String JUMP_LIVE_FOLLOW = "live_follow";
    public static final String JUMP_LIVE_FOUND = "live_found";
    public static final String JUMP_LIVE_ROOM = "live_room";
    public static final String JUMP_QB_ARTICLE_DETAIL = "qb_article_detail";
    public static final String JUMP_QB_IMG = "qb_img";
    public static final String JUMP_QB_NOTICE = "qb_notice";
    public static final String JUMP_QB_POST = "qb_post";
    public static final String JUMP_QB_SUB = "qb_sub";
    public static final String JUMP_QB_TEXT = "qb_text";
    public static final String JUMP_QB_TOPIC = "qb_topic";
    public static final String JUMP_QB_TOPIC_DETAIL = "qb_topic_detail";
    public static final String JUMP_QB_VIDEO = "qb_video";
    public static final String JUMP_SETTINGS = "settings";
    public static final String JUMP_USER_DETAIL = "user_detail";
    public static final String JUMP_WEB = "web";
    public static final String SP_KEY = "event_window";
    public String actionContent;
    public String actionType;
    public String btnTitle;
    public String desc;
    public int hasShow;
    public String iconUrl;
    public String id;
    public String img;
    public int imgHeight;
    public int imgWidth;

    public static EventWindow parseJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        EventWindow eventWindow = new EventWindow();
        eventWindow.id = jSONObject.optString("id");
        JSONObject optJSONObject = jSONObject.optJSONObject(SocialConstants.PARAM_IMG_URL);
        if (optJSONObject != null) {
            eventWindow.img = optJSONObject.optString("url");
            eventWindow.imgWidth = optJSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH);
            eventWindow.imgHeight = optJSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT);
        }
        optJSONObject = jSONObject.optJSONObject("icon");
        if (optJSONObject != null) {
            eventWindow.iconUrl = optJSONObject.optString("url");
        }
        eventWindow.btnTitle = jSONObject.optString("btn_title");
        eventWindow.desc = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        eventWindow.hasShow = jSONObject.optInt("has_show");
        optJSONObject = jSONObject.optJSONObject("btn_action");
        if (optJSONObject == null) {
            return eventWindow;
        }
        eventWindow.actionType = optJSONObject.optString("type");
        eventWindow.actionContent = optJSONObject.optString("data");
        return eventWindow;
    }

    public static EventWindow parseJson(String str) {
        try {
            return parseJson(new JSONObject(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void checkWindowShow(Context context) {
        EventWindow eventWindow = getEventWindow();
        if (eventWindow != null && !eventWindow.a()) {
            EventWindowActivity.launch(context, eventWindow);
            eventWindow.hasShow = 1;
            SharePreferenceUtils.setSharePreferencesValue(SP_KEY, eventWindow.toJson());
        }
    }

    public static EventWindow getEventWindow() {
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(SP_KEY);
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            return null;
        }
        return parseJson(sharePreferencesValue);
    }

    public static boolean hasQiushiTopicEvent() {
        EventWindow eventWindow = getEventWindow();
        if (eventWindow == null || !JUMP_QB_POST.equals(eventWindow.actionType)) {
            return false;
        }
        return true;
    }

    public static void saveEventWindow(String str) {
        Object obj;
        if (TextUtils.isEmpty(str)) {
            obj = null;
        } else {
            obj = 1;
        }
        EventWindow eventWindow = null;
        if (obj != null) {
            eventWindow = parseJson(str);
        }
        if (eventWindow == null) {
            SharePreferenceUtils.setSharePreferencesValue(SP_KEY, "");
            return;
        }
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(SP_KEY);
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            EventWindow parseJson = parseJson(sharePreferencesValue);
            if (parseJson != null && TextUtils.equals(eventWindow.id, parseJson.id)) {
                return;
            }
        }
        SharePreferenceUtils.setSharePreferencesValue(SP_KEY, eventWindow.toJson());
    }

    public static void fetchEventConfig() {
        new HttpTask(null, Constants.ACTIVITY_WINDOW_NEW, new l()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public String toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            jSONObject.put("btn_title", this.btnTitle);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, this.desc);
            jSONObject.put("has_show", this.hasShow);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("url", this.iconUrl);
            jSONObject.put("icon", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("url", this.img);
            jSONObject2.put(IndexEntry.COLUMN_NAME_WIDTH, this.imgWidth);
            jSONObject2.put(IndexEntry.COLUMN_NAME_HEIGHT, this.imgHeight);
            jSONObject.put(SocialConstants.PARAM_IMG_URL, jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("type", this.actionType);
            jSONObject2.put("data", getContentJson());
            jSONObject.put("btn_action", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getContentJson() {
        try {
            return new JSONObject(this.actionContent);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public String getQiushiTopicContent() {
        return getContentJson().optString("content");
    }

    public QiushiTopic getQiushiTopic() {
        int i = -1;
        try {
            i = Integer.parseInt(getActionId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object qiushiTopicContent = getQiushiTopicContent();
        if (i <= 0 || TextUtils.isEmpty(qiushiTopicContent)) {
            return null;
        }
        return new QiushiTopic(i, qiushiTopicContent);
    }

    public String getActionId() {
        return getContentJson().optString("id");
    }

    public void jumpTo(Activity activity) {
        if (this.actionContent != null) {
            String optString = getContentJson().optString("url");
            String optString2 = getContentJson().optString("id");
            if ("im".equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_MESSAGE_ID));
            } else if (JUMP_SETTINGS.equals(this.actionType)) {
                activity.startActivity(new Intent(activity, ActionBarUserSettingNavi.class));
            } else if (JUMP_CIRCLE_DETAIL.equals(this.actionType)) {
                CircleArticleActivity.launch((Context) activity, optString2, true);
            } else if (JUMP_CIRCLE_FOLLOW.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUYOUCIRCLE_ID, 1));
            } else if (JUMP_CIRCLE_NEARBY.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUYOUCIRCLE_ID, 0));
            } else if (JUMP_CIRCLE_POST.equals(this.actionType)) {
                activity.startActivity(new Intent(activity, CirclePublishActivity.class));
            } else if ("circle_topic".equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUYOUCIRCLE_ID, 3));
            } else if (JUMP_CIRCLE_TOPIC_DETAIL.equals(this.actionType)) {
                CircleTopicActivity.launch(activity, optString2);
            } else if (JUMP_CIRCLE_VIDEO.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUYOUCIRCLE_ID, 2));
            } else if (JUMP_CIRLCE_NOTICE.equals(this.actionType)) {
                QiushiNotificationListActivity.gotoQiuyouquan(activity, 0);
            } else if (JUMP_GAME.equals(this.actionType)) {
                if (QsbkApp.currentUser != null) {
                    if (optString.contains("?")) {
                        optString = optString + "&token=" + QsbkApp.currentUser.token;
                    } else {
                        optString = optString + "?token=" + QsbkApp.currentUser.token;
                    }
                }
                Intent intent = new Intent(activity, GameWebViewActivity.class);
                intent.putExtra("url", optString);
                intent.putExtra("title", "游戏中心");
                activity.startActivity(intent);
            } else if (JUMP_LIVE_ALL.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_LIVE_ID, 0));
            } else if (JUMP_LIVE_FOLLOW.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_LIVE_ID, 1));
            } else if (JUMP_LIVE_FOUND.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_LIVE_ID, 2));
            } else if (JUMP_LIVE_ROOM.equals(this.actionType)) {
                r0 = new Intent(activity, LivePullActivity.class);
                long optLong = getContentJson().optLong("origin");
                r0.setClass(activity, LivePullActivity.class);
                r0.putExtra("liveUserId", optString2);
                r0.putExtra("liveUserSource", optLong);
            } else if (JUMP_QB_ARTICLE_DETAIL.equals(this.actionType)) {
                r0 = new Intent();
                r0.setClass(activity, SingleArticle.class);
                r0.putExtra("article_id", optString2);
                activity.startActivity(r0);
            } else if (JUMP_QB_POST.equals(this.actionType)) {
                if (QsbkApp.currentUser == null) {
                    activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
                    return;
                }
                r0 = new Intent(activity, PublishActivity.class);
                Serializable qiushiTopic = getQiushiTopic();
                if (qiushiTopic != null) {
                    r0.putExtra("topic", qiushiTopic);
                }
                activity.startActivity(r0);
            } else if (JUMP_QB_SUB.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUSHI_ID, 0));
            } else if (JUMP_QB_TEXT.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUSHI_ID, 3));
            } else if ("qb_topic".equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUSHI_ID, 2));
            } else if (JUMP_QB_VIDEO.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUSHI_ID, 1));
            } else if (JUMP_QB_IMG.equals(this.actionType)) {
                activity.startActivity(a(activity, MainActivity.TAB_QIUSHI_ID, 4));
            } else if (JUMP_QB_TOPIC_DETAIL.equals(this.actionType)) {
                try {
                    QiushiTopicActivity.Launch(activity, new QiushiTopic(Integer.parseInt(optString2)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (JUMP_QB_NOTICE.equals(this.actionType)) {
                QiushiNotificationListActivity.gotoQiushi(activity, 0);
            } else if (JUMP_USER_DETAIL.equals(this.actionType)) {
                MyInfoActivity.launch(activity, optString2);
            } else if ("web".equals(this.actionType)) {
                WebActivity.launch(activity, optString);
            }
        }
    }

    private Intent a(Context context, String str) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.SELECTED_TAB_ID, str);
        return intent;
    }

    private Intent a(Context context, String str, int i) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.SELECTED_TAB_ID, str);
        intent.putExtra(MainActivity.SECEND_LAYER_TAB_INDEX, i);
        return intent;
    }

    private boolean a() {
        return this.hasShow == 1;
    }
}
