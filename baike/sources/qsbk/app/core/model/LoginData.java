package qsbk.app.core.model;

import android.text.TextUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.PreferenceUtils;

public class LoginData {
    public static String KEY = "logindata";
    public String token;
    public int type;
    public User user;

    public LoginData(User user, String str, int i) {
        this.user = user;
        this.token = str;
        this.type = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoginData loginData = (LoginData) obj;
        if (loginData.user == null) {
            return false;
        }
        if (this.user.getOrigin() == loginData.user.getOrigin() && this.user.getOriginId() == loginData.user.getOriginId()) {
            return true;
        }
        return false;
    }

    public static LoginResponse getLoginDataList() {
        return (LoginResponse) new Gson().fromJson(PreferenceUtils.instance().getString(KEY, null), LoginResponse.class);
    }

    public static void addNewLoginDataIfNotExists(LoginData loginData) {
        Object obj;
        String str = null;
        LoginResponse loginResponse = (LoginResponse) new Gson().fromJson(PreferenceUtils.instance().getString(KEY, null), LoginResponse.class);
        if (loginResponse != null) {
            str = loginResponse.feeds;
        }
        if (str == null) {
            if (loginResponse == null) {
                loginResponse = new LoginResponse();
            }
            List arrayList = new ArrayList();
            loginResponse.feeds = arrayList;
            List list = arrayList;
            obj = loginResponse;
            List list2 = list;
        } else {
            String str2 = str;
            LoginResponse loginResponse2 = loginResponse;
            Object obj2 = str2;
        }
        if (!(list2.contains(loginData) || TextUtils.isEmpty(loginData.user.name) || TextUtils.isEmpty(loginData.user.headurl))) {
            list2.add(0, loginData);
        }
        PreferenceUtils.instance().putString(KEY, new Gson().toJson(obj));
    }

    public static void updateLoginData(User user) {
        if (user != null) {
            LoginResponse loginResponse = (LoginResponse) new Gson().fromJson(PreferenceUtils.instance().getString(KEY, null), LoginResponse.class);
            if (loginResponse != null) {
                List list = loginResponse.feeds;
                if (list != null && list.size() > 0) {
                    int i = 0;
                    while (i < list.size()) {
                        if (((LoginData) list.get(i)).user.getOriginId() == user.getOriginId() && ((LoginData) list.get(i)).user.getOrigin() == user.getOrigin()) {
                            ((LoginData) list.get(i)).user = user;
                            break;
                        }
                        i++;
                    }
                    PreferenceUtils.instance().putString(KEY, new Gson().toJson(loginResponse));
                }
            }
        }
    }

    public static void deleteLoginData(LoginData loginData) {
        LoginResponse loginResponse = (LoginResponse) new Gson().fromJson(PreferenceUtils.instance().getString(KEY, null), LoginResponse.class);
        if (loginResponse != null) {
            List list = loginResponse.feeds;
            if (list != null && list.size() > 0) {
                list.remove(loginData);
                PreferenceUtils.instance().putString(KEY, new Gson().toJson(loginResponse));
            }
        }
    }

    public static void updateToken(User user, String str) {
        if (user != null) {
            LoginResponse loginResponse = (LoginResponse) new Gson().fromJson(PreferenceUtils.instance().getString(KEY, null), LoginResponse.class);
            if (loginResponse != null) {
                List list = loginResponse.feeds;
                if (list != null && list.size() > 0) {
                    int i = 0;
                    while (i < list.size()) {
                        if (((LoginData) list.get(i)).user.getOriginId() == user.getOriginId() && ((LoginData) list.get(i)).user.getOrigin() == user.getOrigin()) {
                            ((LoginData) list.get(i)).token = str;
                            break;
                        }
                        i++;
                    }
                    PreferenceUtils.instance().putString(KEY, new Gson().toJson(loginResponse));
                }
            }
        }
    }
}
