package cn.v6.sixrooms.engine;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.Badge;
import cn.v6.sixrooms.bean.BadgeConfig;
import cn.v6.sixrooms.presenter.BadgeConfigPresenter;
import cn.v6.sixrooms.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

public class ReadBadgeEngine {
    public static final String VERSION = "ver";
    private static BadgeConfig a;

    public ReadBadgeEngine() {
        a();
    }

    private static void a() {
        if (a == null) {
            try {
                InputStream fileInputStream;
                File file = new File(BadgeConfigPresenter.BADGE_FILE_PATH);
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                } else {
                    fileInputStream = V6Coop.getInstance().getContext().getAssets().open(BadgeConfigPresenter.BADGE_FILE_NAME);
                }
                JsonReader jsonReader = new JsonReader(new StringReader(FileUtil.inputStream2String(fileInputStream)));
                jsonReader.setLenient(true);
                a = (BadgeConfig) new Gson().fromJson(jsonReader, BadgeConfig.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Badge> getBadgeList() {
        if (a == null) {
            a();
        }
        if (a != null) {
            return a.getProps();
        }
        return null;
    }
}
