package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.ConfigUpdateBean;
import cn.v6.sixrooms.bean.DownConfigInfo;
import cn.v6.sixrooms.room.BaseConfigPresenter;
import cn.v6.sixrooms.utils.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class GiftNumConfigPresenter extends BaseConfigPresenter {
    private static final String GIFT_NUM_FILE_NAME = "giftNumConfig.json";
    public static final String GIFT_NUM_FILE_PATH = (SDCard + File.separator + APP_NAME_PATH + File.separator + GIFT_NUM_FILE_NAME);
    private static final String SDCard = V6Coop.getInstance().getContext().getFilesDir().toString();

    public void downLoadGiftNumConfig(ConfigUpdateBean configUpdateBean) {
        if (configUpdateBean != null && !TextUtils.isEmpty(configUpdateBean.getNdown())) {
            DownConfigInfo downConfigInfo = new DownConfigInfo();
            downConfigInfo.downUrl = configUpdateBean.getNdown();
            downConfigInfo.md5 = configUpdateBean.getNmd5();
            downConfigInfo.type = configUpdateBean.getNtype();
            downConfigInfo.targetName = GIFT_NUM_FILE_PATH;
            downConfigInfo.targetPath = SDCard + File.separator + APP_NAME_PATH;
            downConfigInfo.name = "礼物数量图形";
            downLoadConfig(downConfigInfo);
        }
    }

    public static String getGiftNumFileName() {
        return GIFT_NUM_FILE_PATH;
    }

    public String getConfigVersion() {
        InputStream fileInputStream;
        FileNotFoundException e;
        IOException e2;
        JSONException e3;
        String str = "0";
        File file = new File(GIFT_NUM_FILE_NAME);
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    JSONObject jSONObject = new JSONObject(FileUtil.inputStream2String(fileInputStream));
                    if (jSONObject.has("ver")) {
                        str = jSONObject.getString("ver");
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str;
                } catch (IOException e5) {
                    e22 = e5;
                    e22.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                } catch (JSONException e6) {
                    e3 = e6;
                    e3.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                }
            } catch (FileNotFoundException e7) {
                e = e7;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (IOException e8) {
                e22 = e8;
                fileInputStream = null;
                e22.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (JSONException e9) {
                e3 = e9;
                fileInputStream = null;
                e3.printStackTrace();
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
