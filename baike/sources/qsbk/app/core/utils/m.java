package qsbk.app.core.utils;

import com.liulishuo.filedownloader.BaseDownloadTask;
import java.io.File;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class m implements Runnable {
    final /* synthetic */ BaseDownloadTask a;
    final /* synthetic */ l b;

    m(l lVar, BaseDownloadTask baseDownloadTask) {
        this.b = lVar;
        this.a = baseDownloadTask;
    }

    public void run() {
        if (this.a.getTag() instanceof String) {
            String str = (String) this.a.getTag();
            if (str.contains("$")) {
                String[] split = str.split("\\$");
                if (split.length == 4 && split[1].equals("zip")) {
                    String targetFilePath = this.a.getTargetFilePath();
                    str = CachePath.REMIX_GIFT_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[0];
                    if (split[3].equals("scene")) {
                        str = CachePath.REMIX_SCENE_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[0];
                    } else if (split[3].equals("market")) {
                        str = CachePath.REMIX_MARKET_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[0];
                    }
                    File file = new File(str);
                    if (file.exists()) {
                        FileUtils.deleteDir(str, true);
                        file.mkdirs();
                    } else {
                        file.mkdirs();
                    }
                    File file2 = new File(targetFilePath);
                    if (file2.exists() && file.exists() && split[2].equals(Md5Utils.getMd5ByFile(file2, true))) {
                        try {
                            CompressUtils.unZipFile(targetFilePath, str, new n(this, targetFilePath, split, str));
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            FileUtils.deleteDir(str, true);
                            return;
                        }
                    }
                    FileUtils.deleteDir(str, true);
                    FileUtils.deleteFileIfExist(targetFilePath);
                }
            }
        }
    }
}
