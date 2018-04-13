package qsbk.app.activity;

import android.text.TextUtils;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;

public class MainActivity$MainFoundStatusManager {
    public static boolean getStatus() {
        return haveGameTips();
    }

    public static boolean haveGameTips() {
        if (!isGameEnable()) {
            return false;
        }
        Object optString = QsbkApp.indexConfig.optString("game_id");
        if (TextUtils.isEmpty(optString) || SharePreferenceUtils.getSharePreferencesBoolValue("game_id_click_" + optString)) {
            return false;
        }
        return true;
    }

    public static boolean isGameEnable() {
        return ConfigManager.getInstance().isTestOnlyChannel() || QsbkApp.indexConfig.optBoolean("game_enable", true);
    }

    public static void onGameClicked() {
        Object optString = QsbkApp.indexConfig.optString("game_id");
        if (!TextUtils.isEmpty(optString)) {
            SharePreferenceUtils.setSharePreferencesValue("game_id_click_" + optString, true);
        }
    }
}
