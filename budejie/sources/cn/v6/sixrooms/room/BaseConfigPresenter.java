package cn.v6.sixrooms.room;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.DownConfigInfo;
import cn.v6.sixrooms.room.gift.GiftNumConfigPresenter;
import cn.v6.sixrooms.room.gift.PoseConfig;
import cn.v6.sixrooms.utils.FileUtil;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.MD5Utils;
import cn.v6.sixrooms.utils.ManifestUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseConfigPresenter {
    protected static String APP_NAME_PATH = ManifestUtil.getSDCradFilePathName();
    private static final String TAG = BaseConfigPresenter.class.getSimpleName();
    protected static final String VERSION = "ver";
    private Runnable downLoadConfigRunnable = new a(this);
    private DownConfigInfo mConfigInfo;

    public abstract String getConfigVersion();

    protected void downLoadConfig(DownConfigInfo downConfigInfo) {
        this.mConfigInfo = downConfigInfo;
        new Thread(this.downLoadConfigRunnable).start();
    }

    private void saveBadgeFile(String str) {
        String str2 = "";
        try {
            InputStream inputStream = new URL(this.mConfigInfo.downUrl).openConnection().getInputStream();
            if (inputStream == null) {
                LogUtils.e(TAG, this.mConfigInfo.name + "更新：网络请求InputStream_____null");
                return;
            }
            byte[] bArr = new byte[1048576];
            File file = new File(str);
            if (file.exists()) {
                str2 = FileUtil.readFile(str);
                file.delete();
            }
            OutputStream fileOutputStream = new FileOutputStream(str);
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            inputStream.close();
            File file2 = new File(str);
            Object fileMD5 = MD5Utils.getFileMD5(file2);
            LogUtils.e(TAG, this.mConfigInfo.name + "更新：本地md5___" + fileMD5);
            LogUtils.e(TAG, this.mConfigInfo.name + "更新：服务端md5___" + this.mConfigInfo.md5);
            if (TextUtils.isEmpty(fileMD5) || !fileMD5.equals(this.mConfigInfo.md5)) {
                if (file2.exists()) {
                    LogUtils.e(TAG, this.mConfigInfo.name + "更新：M5比对结果不同，删除现在的文件恢复原文件");
                    file2.delete();
                    if (!TextUtils.isEmpty(str2)) {
                        FileUtil.writeFile(str, str2);
                        String str3 = TAG;
                        if ((this.mConfigInfo.name + "更新：恢复原先配置文件内容___" + str2) == null) {
                            str2 = "null";
                        }
                        LogUtils.e(str3, str2);
                    }
                }
            } else if (str.equals(GiftNumConfigPresenter.GIFT_NUM_FILE_PATH)) {
                PoseConfig.getInstance().refreshConfig();
            }
        } catch (MalformedURLException e) {
            LogUtils.e(TAG, this.mConfigInfo.name + "：" + e.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            File file3 = new File(str);
            if (file3.exists()) {
                LogUtils.e(TAG, this.mConfigInfo.name + "更新：" + file3.toString() + "发生异常___" + file3.length() + "字节___" + getConfigVersion() + this.mConfigInfo.name + "版本删除~~~");
                file3.delete();
            }
        }
    }
}
