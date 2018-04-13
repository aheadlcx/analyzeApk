package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.ConfigUpdateBean;
import cn.v6.sixrooms.bean.DownConfigInfo;
import cn.v6.sixrooms.room.BaseConfigPresenter;
import cn.v6.sixrooms.utils.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;

public class GiftConfigPresenter extends BaseConfigPresenter {
    private static final String APP_FILES_DIR = V6Coop.getInstance().getContext().getFilesDir().toString();
    public static final String GIFT_FILE_NAME = "giftConfig.json";
    public static final String GIFT_FILE_PATH = (APP_FILES_DIR + File.separator + APP_NAME_PATH + File.separator + GIFT_FILE_NAME);

    public void downLoadGiftConfig(ConfigUpdateBean configUpdateBean) {
        if (configUpdateBean != null && !TextUtils.isEmpty(configUpdateBean.getDown())) {
            DownConfigInfo downConfigInfo = new DownConfigInfo();
            downConfigInfo.downUrl = configUpdateBean.getDown();
            downConfigInfo.md5 = configUpdateBean.getMd5();
            downConfigInfo.type = configUpdateBean.getType();
            downConfigInfo.targetName = GIFT_FILE_PATH;
            downConfigInfo.targetPath = APP_FILES_DIR + File.separator + APP_NAME_PATH;
            downConfigInfo.name = "礼物";
            downLoadConfig(downConfigInfo);
        }
    }

    public String getConfigVersion() {
        InputStream fileInputStream;
        Exception e;
        String str = "0";
        File file = new File(GIFT_FILE_PATH);
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    JSONObject jSONObject = new JSONObject(FileUtil.inputStream2String(fileInputStream));
                    if (jSONObject.has("ver")) {
                        str = jSONObject.getString("ver");
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return str;
                }
            } catch (Exception e4) {
                e = e4;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return str;
    }
}
