package qsbk.app.core.utils;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.DecorateData;
import qsbk.app.core.model.FrameAnimationData;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.model.MarketData;

public class GiftResSync {
    private static final String a = GiftResSync.class.getSimpleName();
    private static final ExecutorService b = Executors.newFixedThreadPool(DeviceUtils.getCpuNumCores() + 1);

    public static void checkUpdate() {
        checkUpdate(false);
    }

    public static void checkUpdate(boolean z) {
        if (!NetworkUtils.getInstance().isNetworkAvailable()) {
            return;
        }
        if ((!z || NetworkUtils.getInstance().isWifiAvailable()) && DeviceUtils.isExternalStorageAvailable() && DeviceUtils.getAvailableExternalMemorySize() / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED >= 50 && DeviceUtils.getAvailableInternalMemorySize() / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED >= 10) {
            c();
        }
    }

    private static void c() {
        List giftList = ConfigInfoUtil.instance().getGiftList();
        Map sceneDataMap = ConfigInfoUtil.instance().getSceneDataMap();
        DecorateData decorateData = ConfigInfoUtil.instance().getDecorateData();
        Map frameAnimations = ConfigInfoUtil.instance().getFrameAnimations();
        Map marketDataMap = ConfigInfoUtil.instance().getMarketDataMap();
        if ((giftList != null && giftList.size() > 0) || (sceneDataMap != null && sceneDataMap.size() > 0)) {
            FileDownloadUtils.setDefaultSaveRootPath(getPath());
            FileDownloadListener lVar = new l();
            List arrayList = new ArrayList();
            a(giftList, arrayList);
            a(sceneDataMap, arrayList);
            downloadDecorateData(decorateData, arrayList);
            downloadFrameAnimations(frameAnimations, arrayList);
            downloadFrameEnterAnimations(marketDataMap, arrayList);
            if (arrayList.size() > 0) {
                FileDownloadQueueSet fileDownloadQueueSet = new FileDownloadQueueSet(lVar);
                fileDownloadQueueSet.disableCallbackProgressTimes();
                fileDownloadQueueSet.setAutoRetryTimes(3);
                fileDownloadQueueSet.downloadTogether(arrayList);
                fileDownloadQueueSet.start();
            }
        }
    }

    public static void encrypt(String str) {
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.getAbsolutePath().endsWith(".ecp")) {
                        try {
                            byte[] fileToBytes = FileUtils.fileToBytes(file2.getAbsolutePath());
                            if (fileToBytes != null) {
                                a(fileToBytes);
                                String absolutePath = file2.getAbsolutePath();
                                if (absolutePath.endsWith(".png")) {
                                    absolutePath = absolutePath.substring(0, absolutePath.length() - 4);
                                }
                                File file3 = new File(absolutePath + ".ecp");
                                file2.delete();
                                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                                fileOutputStream.write(fileToBytes);
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void a(byte[] bArr) {
        for (int i = 0; i < 100; i++) {
            bArr[i] = (byte) (bArr[i] ^ i);
        }
    }

    private static void a(Map<String, LevelData> map, List<BaseDownloadTask> list) {
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                LevelData levelData = (LevelData) entry.getValue();
                if (!TextUtils.isEmpty(levelData.z)) {
                    File[] listFiles = new File(CachePath.REMIX_SCENE_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR + ((String) entry.getKey())).listFiles();
                    if (!PreferenceUtils.instance().getString(PrefrenceKeys.KEY_SCENE_ANIM + ((String) entry.getKey()), "md5").equals(levelData.zm) || listFiles == null || listFiles.length <= 0) {
                        list.add(FileDownloader.getImpl().create(levelData.z).setTag(((String) entry.getKey()) + "$" + "zip$" + levelData.zm + "$" + "scene"));
                    }
                }
            }
        }
    }

    private static void a(List<GiftData> list, List<BaseDownloadTask> list2) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                GiftData giftData = (GiftData) list.get(i);
                if (!TextUtils.isEmpty(giftData.an)) {
                    list2.add(FileDownloader.getImpl().create(giftData.an).setTag(Long.valueOf(giftData.gd)));
                }
            }
        }
    }

    public static String getPath() {
        return CachePath.REMIX_GIFTRESSYNC_PATH;
    }

    public static String getDownloadedGiftResPath(String str) {
        boolean z = false;
        boolean z2 = FileDownloader.getImpl().getStatus(str, getPath()) == (byte) -3;
        String defaultSaveFilePath = FileDownloadUtils.getDefaultSaveFilePath(str);
        if (z2) {
            z = FileUtils.isFileExist(defaultSaveFilePath);
        }
        if (z2 && r1) {
            return defaultSaveFilePath;
        }
        return null;
    }

    public static void downloadDecorateData(DecorateData decorateData, List<BaseDownloadTask> list) {
        if (decorateData != null) {
            list.add(FileDownloader.getImpl().create(decorateData.ic_back).setTag("liveroom_back"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_comment).setTag("liveroom_comment"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_gift).setTag("liveroom_gift"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_share).setTag("liveroom_share"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_screenshot).setTag("liveroom_screenshot"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_main_follow).setTag("main_follow"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_main_home).setTag("main_home"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_main_msg).setTag("main_msg"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_main_page).setTag("main_page"));
            list.add(FileDownloader.getImpl().create(decorateData.ic_main_shot).setTag("main_shot"));
            list.add(FileDownloader.getImpl().create(decorateData.bg_main_top).setTag("main_top"));
            for (int i = 0; i < decorateData.ic_love.size(); i++) {
                list.add(FileDownloader.getImpl().create((String) decorateData.ic_love.get(i)).setTag("liveroom_love_" + i));
            }
        }
    }

    public static void downloadFrameAnimations(Map<Long, FrameAnimationData> map, List<BaseDownloadTask> list) {
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                FrameAnimationData frameAnimationData = (FrameAnimationData) entry.getValue();
                Long l = (Long) entry.getKey();
                if (frameAnimationData.r != null) {
                    File[] listFiles = new File(CachePath.REMIX_GIFT_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR + l).listFiles();
                    if (!PreferenceUtils.instance().getString(PrefrenceKeys.KEY_GIFT_ANIM + l, "md5").equals(frameAnimationData.m) || listFiles == null || listFiles.length != frameAnimationData.f) {
                        list.add(FileDownloader.getImpl().create(frameAnimationData.r).setTag(l + "$" + "zip$" + frameAnimationData.m + "$" + CustomButton.EVENT_TYPE_GIFT));
                    }
                }
            }
        }
    }

    public static void downloadFrameEnterAnimations(Map<Long, MarketData> map, List<BaseDownloadTask> list) {
        if (map != null) {
            for (Entry value : map.entrySet()) {
                MarketData marketData = (MarketData) value.getValue();
                Long valueOf = Long.valueOf(marketData.i);
                if (marketData.r != null) {
                    File[] listFiles = new File(CachePath.REMIX_MARKET_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR + valueOf).listFiles();
                    if (!marketData.r.endsWith("svga") && (!PreferenceUtils.instance().getString(PrefrenceKeys.KEY_MARKET_ANIM + valueOf, "md5").equals(marketData.m) || listFiles == null || listFiles.length <= 0)) {
                        list.add(FileDownloader.getImpl().create(marketData.r).setTag(valueOf + "$" + "zip$" + marketData.m + "$" + "market"));
                    }
                }
            }
        }
    }
}
