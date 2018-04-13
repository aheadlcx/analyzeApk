package qsbk.app.nearby.api;

import android.app.Activity;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.model.FamilyInfo;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.Medal;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.UserInfo;
import qsbk.app.nearby.ui.InfoCompleteActivity;

public class PersonalListener {
    private static final String a = PersonalListener.class.getSimpleName();
    private GetPersonalInfo b;
    private GetPersonalDynamic c;
    private GetPersonalGroup d;
    private GetPersonalQiushi e;
    private GetPersonalTopic f;
    private GetPersonalLive g;
    private GetPersonalMedal h;
    private GetQiushiTopic i;
    private String j;
    private Activity k;
    private boolean l = false;
    private boolean m = false;

    public interface GetPersonalDynamic {
        void getPersonalDynamicFailed();

        void getPersonalDynamicSucc(CircleArticle circleArticle, int i, int i2, int i3);
    }

    public interface GetPersonalGroup {
        void getPersonalGroupFailed();

        void getPersonalGroupSucc(ArrayList<GroupInfo> arrayList, int i);
    }

    public interface GetPersonalInfo {
        void getPersonalInfoFailed();

        void getPersonalInfoSucc(UserInfo userInfo);
    }

    public interface GetPersonalLive {
        void getPersonalLiveFailed();

        void getPersonalLiveSucc(BaseUserInfo[] baseUserInfoArr, int i, int i2, int i3, FamilyInfo familyInfo);
    }

    public interface GetPersonalMedal {
        void getPersonalMedalFailed();

        void getPersonalMedalSucc(Medal[] medalArr, int i);
    }

    public interface GetPersonalQiushi {
        void getPersonalQiushiFailed();

        void getPersonalQiushiSucc(ArrayList<Article> arrayList, int i, int i2, int i3, int i4, int i5);
    }

    public interface GetPersonalTopic {
        void getPersonalTopicFailed();

        void getPersonalTopicSucc(ArrayList<CircleTopic> arrayList, int i);
    }

    public interface GetQiushiTopic {
        void getQiushiTopicFailed();

        void getQiushiTopicSucc(List<QiushiTopic> list);
    }

    public interface GetPersonalScore {
        void getPersonalScoreFailed();

        void getPersonalScoreSucc(int i, int i2);
    }

    public PersonalListener(String str, Activity activity) {
        this.j = str;
        this.k = activity;
    }

    public PersonalListener(String str, Activity activity, boolean z) {
        this.j = str;
        this.k = activity;
        this.m = z;
    }

    public void startGetPersonalInfo() {
        new SimpleHttpTask(String.format(Constants.PERSONAL_INFO_URL, new Object[]{this.j}), new i(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a() {
        Intent intent = new Intent(this.k, InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        this.k.startActivityForResult(intent, 1);
    }

    public void startGetPersonalDynamic(double d, double d2) {
        String format;
        if (this.m) {
            format = String.format(Constants.PERSONAL_DYNAMIC_URL + "&login=1", new Object[]{this.j, String.valueOf(d), String.valueOf(d2)});
        } else {
            format = String.format(Constants.PERSONAL_DYNAMIC_URL, new Object[]{this.j, String.valueOf(d), String.valueOf(d2)});
        }
        new SimpleHttpTask(format, new j(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startGetQiushiTopic() {
        new SimpleHttpTask(String.format(Constants.QIUSHI_TOPIC_USER_SUB, new Object[]{this.j}), new k(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startGetPersonalGroup() {
        String format;
        if (this.m) {
            format = String.format(Constants.PERSONAL_GROUP_URL + "&login=1", new Object[]{this.j});
        } else {
            format = String.format(Constants.PERSONAL_GROUP_URL, new Object[]{this.j});
        }
        new SimpleHttpTask(format, new l(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startGetPersonalQiushi() {
        String format;
        if (this.m) {
            format = String.format(Constants.PERSONAL_QIUSHI_URL + "?login=1", new Object[]{this.j});
        } else {
            format = String.format(Constants.PERSONAL_QIUSHI_URL, new Object[]{this.j});
        }
        new SimpleHttpTask(format, new m(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startGetPersonalTopic() {
        String format;
        if (this.m) {
            format = String.format(Constants.PERSONAL_TOPIC_URL + "?login=1", new Object[]{this.j});
        } else {
            format = String.format(Constants.PERSONAL_TOPIC_URL, new Object[]{this.j});
        }
        new SimpleHttpTask(format, new n(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startGetPersonalLive() {
        new SimpleHttpTask(String.format(Constants.LIVE_PERSONAL_INFO, new Object[]{this.j}), new o(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startGetPersonaMedal() {
        new SimpleHttpTask(String.format(Constants.PERSONAL_MEDAL_OTHER_URL, new Object[]{this.j}) + "?count=3", new p(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void stop() {
    }

    public void setOnListener(GetPersonalInfo getPersonalInfo, GetPersonalDynamic getPersonalDynamic, GetPersonalQiushi getPersonalQiushi, GetPersonalGroup getPersonalGroup, GetPersonalLive getPersonalLive, GetPersonalMedal getPersonalMedal, GetQiushiTopic getQiushiTopic) {
        this.b = getPersonalInfo;
        this.c = getPersonalDynamic;
        this.e = getPersonalQiushi;
        this.d = getPersonalGroup;
        this.g = getPersonalLive;
        this.h = getPersonalMedal;
        this.i = getQiushiTopic;
    }

    public void setOnListener(GetPersonalInfo getPersonalInfo, GetPersonalDynamic getPersonalDynamic, GetPersonalQiushi getPersonalQiushi, GetPersonalGroup getPersonalGroup, GetPersonalTopic getPersonalTopic, GetPersonalLive getPersonalLive, GetPersonalMedal getPersonalMedal, GetQiushiTopic getQiushiTopic) {
        this.b = getPersonalInfo;
        this.c = getPersonalDynamic;
        this.e = getPersonalQiushi;
        this.d = getPersonalGroup;
        this.f = getPersonalTopic;
        this.g = getPersonalLive;
        this.h = getPersonalMedal;
        this.i = getQiushiTopic;
    }

    public boolean getIsNoLogin() {
        return this.m;
    }

    public void setNoLogin(boolean z) {
        this.m = z;
    }
}
