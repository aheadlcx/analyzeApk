package cn.v6.sixrooms.room.gift;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.DownConfigInfo;
import cn.v6.sixrooms.room.download.Downloader;
import cn.v6.sixrooms.room.download.Downloader.DownloadListener;
import cn.v6.sixrooms.room.interfaces.ISticker;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.MD5Utils;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StickerGiftControl {
    private static final int STICKER_DURATION = 4000;
    private static final String TAG = "StickerGiftControl";
    private ISticker mCallBack;
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    static class StickerRunnable implements Runnable {
        private Gift gift;
        private ISticker mCallBack;

        public StickerRunnable(Gift gift, ISticker iSticker) {
            this.gift = gift;
            this.mCallBack = iSticker;
        }

        public void run() {
            String anigift = this.gift.getAnigift();
            LogUtils.i(StickerGiftControl.TAG, "url:" + anigift + " ;num: " + this.gift.getNum());
            if (!StickerGiftControl.isLoad(anigift) || this.mCallBack == null) {
                DownConfigInfo downConfigInfo = new DownConfigInfo();
                downConfigInfo.targetName = StickerGiftControl.url2FileName(anigift);
                downConfigInfo.targetPath = StickerGiftControl.getDirPath();
                downConfigInfo.downUrl = anigift;
                new Downloader(downConfigInfo, new DownloadListener() {
                    public void onLoadingFailed(DownConfigInfo downConfigInfo, String str, Exception exception) {
                        File file = new File(StickerGiftControl.getDirPath() + StickerGiftControl.url2FileName(downConfigInfo.downUrl));
                        if (file.exists()) {
                            file.delete();
                        }
                        LogUtils.i(StickerGiftControl.TAG, "url:" + StickerRunnable.this.gift.getAnigift() + " 下载失败");
                    }

                    public void onLoadingComplete(DownConfigInfo downConfigInfo) {
                        LogUtils.i(StickerGiftControl.TAG, "url:" + StickerRunnable.this.gift.getAnigift() + " 下载成功");
                        if (StickerRunnable.this.mCallBack != null) {
                            StickerGiftControl.showSticker(StickerRunnable.this.gift, StickerRunnable.this.mCallBack);
                        }
                    }
                }).downloadSync();
                return;
            }
            StickerGiftControl.showSticker(this.gift, this.mCallBack);
        }
    }

    public StickerGiftControl(ISticker iSticker) {
        this.mCallBack = iSticker;
    }

    public void addGift(Gift gift) {
        if (this.mExecutorService != null && !this.mExecutorService.isShutdown()) {
            this.mExecutorService.submit(new StickerRunnable(gift, this.mCallBack));
        }
    }

    private static boolean isLoad(String str) {
        return new File(getDirPath() + url2FileName(str)).exists();
    }

    private static String getDirPath() {
        return V6Coop.getInstance().getContext().getFilesDir().toString() + File.separator + "gift_sticker/";
    }

    private static String url2FileName(String str) {
        return MD5Utils.getMD5Str(str) + ".zip";
    }

    private static void showSticker(Gift gift, ISticker iSticker) {
        int num = gift.getNum();
        for (int i = 0; i < num; i++) {
            LogUtils.i(TAG, "贴脸 start url:" + gift.getAnigift() + " ;i: " + i + " time:" + (System.currentTimeMillis() / 1000));
            iSticker.onGiftItemSelect(url2FileName(gift.getAnigift()), getDirPath());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iSticker.onGiftItemSelect(null, null);
            LogUtils.i(TAG, "贴脸 end url:" + gift.getAnigift() + " ;i: " + i + " time:" + (System.currentTimeMillis() / 1000));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }
}
