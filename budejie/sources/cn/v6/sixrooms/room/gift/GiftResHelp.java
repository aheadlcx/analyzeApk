package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.room.downloader.GiftResDownloader;
import cn.v6.sixrooms.room.downloader.GiftResDownloader.GiftResDownLoadCallback;
import cn.v6.sixrooms.utils.FileUtil;
import cn.v6.sixrooms.utils.MD5Utils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GiftResHelp {
    private static Gson mGson = new Gson();
    private GiftResDownLoadCallback mCallback;
    private List<Gift> mDownLoadList = new ArrayList();
    private boolean mIsDownLoading = false;

    public GiftResHelp(GiftResDownLoadCallback giftResDownLoadCallback) {
        this.mCallback = giftResDownLoadCallback;
        this.mDownLoadList = new ArrayList();
    }

    public void downloadNextRes() {
        Gift gift = null;
        synchronized (this) {
            if (this.mDownLoadList.size() > 0) {
                gift = (Gift) this.mDownLoadList.remove(0);
            }
        }
        if (gift != null) {
            this.mIsDownLoading = true;
            new GiftResDownloader(gift, this.mCallback).downAsynFile();
        }
    }

    public void enqueue(Gift gift) {
        synchronized (this) {
            this.mDownLoadList.add(gift);
        }
        if (!this.mIsDownLoading) {
            downloadNextRes();
        }
    }

    public void done() {
        this.mIsDownLoading = false;
        downloadNextRes();
    }

    public static boolean checkGiftResMd5(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        List asList = Arrays.asList(file.listFiles());
        File file2 = new File(file, "verification.json");
        try {
            if (!file2.exists()) {
                return false;
            }
            Map verificationMap = getVerificationMap(new InputStreamReader(new FileInputStream(file2)));
            if (verificationMap == null) {
                return false;
            }
            if (verificationMap.size() > asList.size() - 1) {
                return false;
            }
            for (String str2 : verificationMap.keySet()) {
                if (!checkFileMd5Value(asList, str2, (String) verificationMap.get(str2))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean checkFileMd5Value(List<File> list, String str, String str2) {
        for (File file : list) {
            if (str.equals(file.getName())) {
                return MD5Utils.getFileMD5(file).equals(str2);
            }
        }
        return false;
    }

    private static Map<String, String> getVerificationMap(InputStreamReader inputStreamReader) throws IOException {
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(FileUtil.inputStream2String(inputStreamReader)));
            jsonReader.setLenient(true);
            return (Map) mGson.fromJson(jsonReader, new GiftResHelp$1().getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
