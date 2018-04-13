package cn.v6.sixrooms.utils;

import android.text.TextUtils;
import cn.v6.sixrooms.constants.UrlStrs;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

final class i implements Runnable {
    final /* synthetic */ GiftDownload a;

    i(GiftDownload giftDownload) {
        this.a = giftDownload;
    }

    public final void run() {
        int i = 0;
        HttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            HttpResponse execute = defaultHttpClient.execute(new HttpGet(UrlStrs.GIFT_URL));
            HttpEntity entity = execute.getEntity();
            int parseInt = Integer.parseInt(execute.getHeaders("Content-Length")[0].getValue());
            File file = new File(this.a.c);
            if (file.exists() && file.length() == ((long) parseInt)) {
                this.a.e.finsh(this.a.c, parseInt);
            } else if (entity != null) {
                if (!TextUtils.isEmpty(this.a.b)) {
                    file = new File(this.a.b);
                    if (!file.exists()) {
                        file.mkdirs();
                        LogUtils.i(getClass().getSimpleName(), "创建礼物图片文件夹");
                    }
                }
                LogUtils.i(getClass().getSimpleName(), "礼物图片文下载开始");
                this.a.g = System.currentTimeMillis();
                InputStream content = entity.getContent();
                byte[] bArr = new byte[1048576];
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.a.c));
                int read;
                do {
                    read = content.read(bArr);
                    if (read > 0) {
                        bufferedOutputStream.write(bArr, 0, read);
                        i += read;
                        continue;
                    }
                } while (read > 0);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                content.close();
                defaultHttpClient.getConnectionManager().shutdown();
                LogUtils.i(getClass().getSimpleName(), "tm = " + (System.currentTimeMillis() - this.a.g) + "礼物图片文下载结束, downloadedSize = " + i + ", totalSize = " + parseInt);
                if (i == parseInt) {
                    LogUtils.i(getClass().getSimpleName(), "礼物图片文下载成功，totalSize = " + parseInt);
                    this.a.e.finsh(this.a.c, parseInt);
                    return;
                }
                LogUtils.i(getClass().getSimpleName(), "礼物图片文下载失败，totalSize = " + parseInt);
                this.a.e.error();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            this.a.e.error();
        } catch (IOException e2) {
            e2.printStackTrace();
            this.a.e.error();
        }
    }
}
