package qsbk.app.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.fragments.QiushiListFragment;

public class VideoLoadConfig {
    public static final String[] DISPLAY_NAME = new String[]{"仅WIFI", "手动", "自动"};
    public static final int VIDEO_PLAY_MODE_AUTO_PLAY = 2;
    public static final String VIDEO_PLAY_MODE_CHANGED = "video_play_mode_changed";
    public static final int VIDEO_PLAY_MODE_CLICK_TO_PLAY = 1;
    public static final int VIDEO_PLAY_MODE_WIFI_ONLY = 0;
    private static final String[] a = new String[]{"仅在WIFI下自动播放", "点击播放", "总是自动播放"};
    private static Integer b = null;

    private VideoLoadConfig() {
    }

    public static boolean isAutoPlay(Context context) {
        int state = getState();
        if (!(state == 2 && HttpUtils.netIsAvailable()) && state == 0 && HttpUtils.isWifi(context)) {
        }
        return true;
    }

    public static String getName() {
        return DISPLAY_NAME[getState()];
    }

    public static String getName(int i) {
        if (i < 0 || i > DISPLAY_NAME.length - 1) {
            return "";
        }
        return DISPLAY_NAME[i];
    }

    public static int getState() {
        if (b != null) {
            return b.intValue();
        }
        b = Integer.valueOf(FilePreferenceUtils.getPreferencesIntValue("play_video_type"));
        return b.intValue();
    }

    public static void setState(int i) {
        b = Integer.valueOf(i);
        FilePreferenceUtils.setPreferencesValue("play_video_type", Integer.valueOf(i));
    }

    public static void showSelectDialog(Context context, OnClickListener onClickListener) {
        int state = getState();
        Builder builder = new Builder(context);
        builder.setSingleChoiceItems(a, state, new bp(state, onClickListener));
        builder.show();
    }

    public static void showSelectDialog(Context context) {
        showSelectDialog(context, null);
    }

    public static void setVideoPlayModeChanged() {
        SharePreferenceUtils.setSharePreferencesValue(QiushiListFragment.VIDEO_PLAY_MODE_TIP_SHOW, false);
        DebugUtil.debug("luolong", "setVideoPlayModeChanged, " + SharePreferenceUtils.getSharePreferencesBoolValue(QiushiListFragment.VIDEO_PLAY_MODE_TIP_SHOW));
    }
}
