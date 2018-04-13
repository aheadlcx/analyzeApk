package cn.v6.sixrooms.room.presenter;

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

public class StickerConfigPresenter extends BaseConfigPresenter {
    private static final String a = V6Coop.getInstance().getContext().getFilesDir().toString();
    private static final String b = (a + File.separator + APP_NAME_PATH + File.separator + "stickerConfig.json");

    public static String getStickerConfigPath() {
        return b;
    }

    public void downLoadPasterConfig(ConfigUpdateBean configUpdateBean) {
        if (configUpdateBean != null && !TextUtils.isEmpty(configUpdateBean.getFdown())) {
            DownConfigInfo downConfigInfo = new DownConfigInfo();
            downConfigInfo.downUrl = configUpdateBean.getFdown();
            downConfigInfo.md5 = configUpdateBean.getFmd5();
            downConfigInfo.type = configUpdateBean.getFtype();
            downConfigInfo.targetName = b;
            downConfigInfo.targetPath = a + File.separator + APP_NAME_PATH;
            downConfigInfo.name = "贴纸";
            downLoadConfig(downConfigInfo);
        }
    }

    public String getConfigVersion() {
        InputStream fileInputStream;
        FileNotFoundException e;
        IOException e2;
        JSONException e3;
        String str = "0";
        File file = new File(b);
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
