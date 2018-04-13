package qsbk.app.api;

import android.app.Activity;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.model.Article;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.UserInfo;

public class QiuYouListener {
    private static final String b = QiuYouListener.class.getSimpleName();
    int a = 1;
    private String c = Constants.REL_GET_FRIENDS;
    private String d = Constants.REL_GET_FOLLOWS;
    private String e = Constants.REL_GET_FANS;
    private String f = (Constants.URL_MY_GROUP_LIST + "?page=1");
    private boolean g = false;
    private String h;
    private Activity i;

    public interface GetFans {
        void getPersonalDynamicFailed();

        void getPersonalDynamicSucc();
    }

    public interface GetFollowed {
        void getPersonalQiushiFailed();

        void getPersonalQiushiSucc(ArrayList<Article> arrayList, int i);
    }

    public interface GetFriend {
        void getPersonalInfoFailed();

        void getPersonalInfoSucc(UserInfo userInfo);
    }

    public interface GetGroup {
        void getPersonalGroupFailed();

        void getPersonalGroupSucc(ArrayList<GroupInfo> arrayList, int i);
    }

    public QiuYouListener(String str, Activity activity) {
        this.h = str;
        this.i = activity;
    }

    public QiuYouListener(Activity activity) {
        this.i = activity;
    }
}
