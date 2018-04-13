package qsbk.app.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class CompressUtils {

    public interface CompressListener {
        void onFinished();
    }

    public static void unZipFile(String str, String str2, CompressListener compressListener) throws IOException, FileNotFoundException, ZipException {
        ZipFile zipFile = new ZipFile(str);
        Enumeration entries = zipFile.entries();
        long currentTimeMillis = System.currentTimeMillis();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            String name = zipEntry.getName();
            String str3 = str2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + name;
            if (zipEntry.isDirectory()) {
                LogUtils.d("正在创建解压目录 - " + name);
                File file = new File(str3);
                if (!file.exists()) {
                    file.mkdirs();
                }
            } else {
                LogUtils.d("正在创建解压文件 - " + name);
                File file2 = new File(str3.substring(0, str3.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR)));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + name));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                byte[] bArr = new byte[1024];
                for (int read = bufferedInputStream.read(bArr); read != -1; read = bufferedInputStream.read(bArr)) {
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();
            }
        }
        zipFile.close();
        LogUtils.e("uncompress time: ", (System.currentTimeMillis() - currentTimeMillis) + "");
        if (compressListener != null) {
            compressListener.onFinished();
        }
    }
}
