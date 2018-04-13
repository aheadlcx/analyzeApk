package qsbk.app.core.utils;

import android.os.Environment;

public class CachePath {
    public static final String REMIX_GIFTRESSYNC_PATH = (SDCARD_PATH + "/DCIM/.remixGift");
    public static final String REMIX_GIFT_PATH = (REMIX_PATH + "/.Animation");
    public static final String REMIX_MARKET_PATH = (REMIX_PATH + "/.Market");
    public static final String REMIX_PATH = (SDCARD_PATH + "/Remix");
    public static final String REMIX_SCENE_PATH = (REMIX_PATH + "/.Scene");
    public static final String SDCARD_PATH = (Environment.getExternalStorageDirectory() + "");
}
