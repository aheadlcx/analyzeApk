package qsbk.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.g;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.activity.group.SplashAdStatUtil;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.GameWebViewActivity;
import qsbk.app.im.IMTimer;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.model.Announcement;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.QiushiTopic;

public class SplashAdManager {
    public static final String ANNOUNCEMENT_STRING = "_announcement_";
    private static SplashAdManager a;
    public static Announcement mAnnouncement;
    private ArrayList<Runnable> b = new ArrayList();
    private SplashAdGroup c;
    private AtomicInteger d = new AtomicInteger(0);
    private SharedPreferences e = QsbkApp.getInstance().getSharedPreferences("splash_ad_times", 0);

    public interface Filter {
        boolean filter(SplashAdItem splashAdItem);
    }

    public static class SplashAdGroup {
        private ArrayList<SplashAdItem> a;
        private HashMap<String, SplashAdItem> b = new HashMap();
        public int version;

        public SplashAdItem getActivityItem(String str) {
            return (SplashAdItem) this.b.get(str);
        }

        public boolean isLoaded(SplashAdItem splashAdItem) {
            return new File(SplashAdManager.getDataDir(), "I" + splashAdItem.picUrl.hashCode()).exists();
        }

        public SplashAdItem getValidOneBy(Filter filter) {
            if (this.a == null || this.a.size() == 0) {
                return null;
            }
            int i;
            int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                SplashAdItem splashAdItem = (SplashAdItem) it.next();
                if (splashAdItem.startTime > correctTime || splashAdItem.endTime <= correctTime) {
                    i = i2;
                } else if (splashAdItem.picUrl != null && (filter == null || !filter.filter(splashAdItem))) {
                    arrayList.add(splashAdItem);
                    i = i2 + splashAdItem.weight;
                }
                i2 = i;
            }
            if (arrayList.size() == 0) {
                return null;
            }
            if (i2 > 0) {
                i = (int) (((double) i2) * Math.random());
                Iterator it2 = arrayList.iterator();
                i2 = i;
                while (it2.hasNext()) {
                    splashAdItem = (SplashAdItem) it2.next();
                    if (i2 < splashAdItem.weight) {
                        return splashAdItem;
                    }
                    i2 -= splashAdItem.weight;
                }
            }
            return (SplashAdItem) arrayList.get((int) (Math.random() * ((double) arrayList.size())));
        }

        public String toString() {
            String str;
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder append = stringBuilder.append("splash ");
            if (this.a == null) {
                str = " null ";
            } else {
                str = " size =  " + this.a.size();
            }
            append.append(str).append(this.b.size() > 0 ? "activitys has items" : "activitys null").append("version = " + this.version);
            return stringBuilder.toString();
        }
    }

    public static class SplashAdItem implements Serializable {
        public static final String AD_CIRCLE = "circle_article";
        public static final String AD_DOWNLOAD = "download";
        public static final String AD_LIVE = "live";
        public static final String AD_QIUSHI = "qiushi_article";
        public static final String AD_QIUSHI_TOPIC = "qiushi_topic";
        public static final String AD_TOPIC = "topic";
        public static final String AD_WEB = "web";
        public static final String AD_WEB_ACT = "web_act";
        public static final String AD_WEB_GAME = "web_game";
        public static final String TAB_CIRCLE = "circle";
        public static final String TAB_IM = "im";
        public static final String TAB_LIVE = "live";
        public static final String TAB_ME = "me";
        public static final String TAB_QIUSHI = "qiushi";
        public int delay;
        public int endTime;
        public int id;
        public int interval;
        public boolean needLogin;
        public boolean needShare;
        public boolean need_nav = true;
        public String picUrl;
        public int startTime;
        public String subType;
        public String topicId;
        public String type;
        public String webLink;
        public int weight;

        public SplashAdItem(JSONObject jSONObject) {
            boolean z = true;
            try {
                this.id = jSONObject.getInt("ad_id");
                this.startTime = jSONObject.getInt("start_date");
                this.endTime = jSONObject.getInt("end_date");
                this.weight = jSONObject.getInt("weight");
                this.interval = jSONObject.getInt(g.az);
                this.delay = jSONObject.optInt("delay");
                this.type = jSONObject.getString("redirect_type");
                if (jSONObject.optInt("need_share") == 0) {
                    z = false;
                }
                this.needShare = z;
                this.picUrl = jSONObject.getString("pic_url");
                this.webLink = jSONObject.optString("web_info");
                this.topicId = jSONObject.optString("topic_info");
                this.need_nav = jSONObject.optBoolean("need_nav", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onActionClick(Activity activity) {
            if (this.needLogin && QsbkApp.currentUser == null) {
                activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            } else if ("web".equals(this.type)) {
                SimpleWebActivity.launch((Context) activity, this.webLink, this.needShare, this.need_nav);
            } else if ("topic".equals(this.type)) {
                CircleTopic circleTopic = new CircleTopic();
                circleTopic.id = this.topicId;
                circleTopic.content = "";
                CircleTopicActivity.launch(activity, circleTopic, 0);
            } else if (AD_WEB_GAME.equals(this.type)) {
                if (!(QsbkApp.currentUser == null || TextUtils.isEmpty(this.webLink))) {
                    if (this.webLink.contains("?")) {
                        this.webLink += "&token=" + QsbkApp.currentUser.token;
                    } else {
                        this.webLink += "?token=" + QsbkApp.currentUser.token;
                    }
                }
                GameWebViewActivity.launch(activity, this.webLink, "", AD_WEB_GAME);
            } else if ("live".equals(this.type)) {
                LivePullLauncher.from((Context) activity).with(this.webLink).with(this.topicId, true).launch(0);
            } else if (AD_WEB_ACT.equals(this.type)) {
                SimpleWebActivity.launch((Context) activity, this.webLink, this.needShare, this.need_nav);
            } else if ("download".equals(this.type)) {
                Util.downloadAPKwithDialog(activity, this.webLink);
            } else if (AD_CIRCLE.equals(this.type)) {
                CircleArticleActivity.launch((Context) activity, this.webLink, false);
            } else if (AD_QIUSHI.equals(this.type)) {
                SingleArticle.launch(activity, this.webLink);
            } else if (AD_QIUSHI_TOPIC.equals(this.type)) {
                int parseInt;
                try {
                    parseInt = Integer.parseInt(this.webLink);
                } catch (Exception e) {
                    e.printStackTrace();
                    parseInt = 0;
                }
                if (parseInt > 0) {
                    QiushiTopicActivity.Launch(activity, new QiushiTopic(parseInt));
                }
            }
        }
    }

    private SplashAdManager() {
    }

    public static SplashAdManager instance() {
        if (a == null) {
            a = new SplashAdManager();
        }
        return a;
    }

    public static File getDataDir() {
        String sDPath = DeviceUtils.getSDPath();
        if (sDPath == null || sDPath.length() == 0) {
            sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
        }
        String str;
        if (QsbkApp.currentUser == null) {
            str = "";
        } else {
            str = QsbkApp.currentUser.userId;
        }
        File file = new File(sDPath + File.separator + "qsbk/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getFile() {
        return new File(getDataDir(), Integer.toHexString("splash_ad".hashCode()));
    }

    public static void clearTime() {
        instance().e.edit().clear().apply();
    }

    public boolean isLoading() {
        return this.d.get() == 1;
    }

    public boolean isLoadFail() {
        return this.d.get() == 4;
    }

    public boolean isLoadSuc() {
        return this.d.get() == 2;
    }

    public void reset() {
        b(0);
    }

    public boolean markTimeout() {
        if (isLoading()) {
            SplashAdStatUtil.loadSelfTimeout();
        }
        return a(4);
    }

    private boolean a(int i) {
        int i2 = this.d.get();
        if (isLoadFail() || isLoadSuc() || i <= i2) {
            return false;
        }
        b(i);
        return true;
    }

    private void b(int i) {
        this.d.set(i);
    }

    private void a(String str) {
        File file = getFile();
        if (str == null) {
            file.delete();
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
            file.setLastModified(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String a() {
        File file = getFile();
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    } else {
                        bufferedReader.close();
                        return stringBuilder.toString();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public void loadSplashAd() {
        if (this.d.get() == 0) {
            b(1);
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new az(this));
        }
    }

    public void doTaskOnLoaded(Runnable runnable) {
        this.b.add(runnable);
    }

    public void cancelTask(Runnable runnable) {
        this.b.remove(runnable);
    }

    private SplashAdGroup a(JSONObject jSONObject) {
        int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
        SplashAdGroup splashAdGroup = new SplashAdGroup();
        ArrayList arrayList = new ArrayList();
        try {
            int i;
            boolean z;
            JSONArray jSONArray = jSONObject.getJSONArray("items");
            int length = jSONArray.length();
            for (i = 0; i < length; i++) {
                SplashAdItem splashAdItem = new SplashAdItem();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                splashAdItem.id = jSONObject2.optInt("ad_id");
                splashAdItem.startTime = jSONObject2.optInt("start_date");
                splashAdItem.endTime = jSONObject2.optInt("end_date");
                splashAdItem.weight = jSONObject2.optInt("weight");
                splashAdItem.interval = jSONObject2.optInt(g.az);
                splashAdItem.delay = jSONObject2.optInt("delay");
                splashAdItem.type = jSONObject2.optString("redirect_type");
                if (jSONObject2.optInt("need_share") != 0) {
                    z = true;
                } else {
                    z = false;
                }
                splashAdItem.needShare = z;
                splashAdItem.picUrl = jSONObject2.optString("pic_url");
                splashAdItem.webLink = jSONObject2.optString("web_info");
                splashAdItem.topicId = jSONObject2.optString("topic_info");
                if (splashAdItem.endTime > correctTime) {
                    arrayList.add(splashAdItem);
                }
            }
            splashAdGroup.a = arrayList;
            JSONArray optJSONArray = jSONObject.optJSONArray("actions");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    try {
                        SplashAdItem splashAdItem2 = new SplashAdItem();
                        splashAdItem2.id = optJSONObject.getInt("ad_id");
                        splashAdItem2.startTime = optJSONObject.optInt("start_date");
                        splashAdItem2.endTime = optJSONObject.optInt("end_date");
                        splashAdItem2.type = optJSONObject.optString("redirect_type");
                        if (optJSONObject.optInt("need_share") != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        splashAdItem2.needShare = z;
                        splashAdItem2.picUrl = optJSONObject.optString("pic_url");
                        splashAdItem2.webLink = optJSONObject.optString("web_info_android");
                        splashAdItem2.topicId = optJSONObject.optString("topic_info");
                        splashAdItem2.subType = optJSONObject.optString("sub_type");
                        if (optJSONObject.optInt("need_login") != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        splashAdItem2.needLogin = z;
                        splashAdItem2.need_nav = optJSONObject.optBoolean("need_nav", true);
                        if (splashAdItem2.endTime > correctTime) {
                            splashAdGroup.b.put(splashAdItem2.subType, splashAdItem2);
                        }
                    } catch (JSONException e) {
                    }
                }
            }
            splashAdGroup.version = jSONObject.getInt("version_id");
            return splashAdGroup;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Announcement b(JSONObject jSONObject) {
        Announcement parseJson;
        if (jSONObject == null || jSONObject.optJSONObject("announcement") == null) {
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(ANNOUNCEMENT_STRING);
            if (TextUtils.isEmpty(sharePreferencesValue)) {
                return null;
            }
            try {
                parseJson = Announcement.parseJson(new JSONObject(sharePreferencesValue));
                if (parseJson.end < new Date().getTime() / 1000) {
                    return null;
                }
                return parseJson;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        parseJson = Announcement.parseJson(jSONObject.optJSONObject("announcement"));
        if (parseJson.end < new Date().getTime() / 1000) {
            return null;
        }
        SharePreferenceUtils.setSharePreferencesValue(ANNOUNCEMENT_STRING, jSONObject.toString());
        return parseJson;
    }

    private void a(SplashAdGroup splashAdGroup) {
        File dataDir = getDataDir();
        Iterator it = splashAdGroup.a.iterator();
        while (it.hasNext()) {
            SplashAdItem splashAdItem = (SplashAdItem) it.next();
            if (splashAdItem.picUrl != null) {
                String str = splashAdItem.picUrl;
                File file = new File(dataDir, "I" + str.hashCode());
                if (!file.exists()) {
                    File file2 = new File(dataDir, "T" + str.hashCode() + "");
                    UpdateHelper.getInstance().download(str, file2, new bb(this, file, file2));
                }
            }
        }
    }

    public SplashAdGroup getGroup() {
        SplashAdGroup splashAdGroup;
        synchronized (this) {
            splashAdGroup = this.c;
        }
        return splashAdGroup;
    }

    private void b(SplashAdGroup splashAdGroup) {
        synchronized (this) {
            this.c = splashAdGroup;
        }
    }

    public int getLastShowTime(int i) {
        return this.e.getInt(String.valueOf(i), 0);
    }

    public void setLastShowTime(int i, int i2) {
        this.e.edit().putInt(String.valueOf(i), i2).apply();
    }
}
