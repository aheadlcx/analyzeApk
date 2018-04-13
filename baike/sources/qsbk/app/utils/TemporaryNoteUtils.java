package qsbk.app.utils;

import android.content.SharedPreferences;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

public class TemporaryNoteUtils {
    public static SharedPreferences getPreferences() {
        return QsbkApp.getInstance().getSharedPreferences("temp_note", 0);
    }

    public static boolean isLoaded() {
        return getPreferences().getBoolean("loaded", false);
    }

    public static void loadSwitch(int i, SimpleCallBack simpleCallBack) {
        new SimpleHttpTask(String.format(Constants.URL_SET_GROUP_MSG_SWITCH_TEMP + "?page=%d", new Object[]{Integer.valueOf(i)}), simpleCallBack).execute();
    }

    public static void loadAll(SimpleCallBack simpleCallBack) {
        loadSwitch(0, new bc(simpleCallBack));
    }
}
