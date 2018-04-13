package com.zhihu.matisse.thumbnail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.WorkerThread;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ThumbnailGenerator {
    private static final int HEIGHT = 300;
    private static final String THUMBNAIL_WORK_FOLDER_NAME = ".thumbnail";
    private static final int WIDTH = 300;
    private Callback mCallback;
    private ThumbnailHandler mThumbnailHandler;
    private Looper mThumbnailLooper;
    private File mWorkFolder;

    interface Callback {
        void onThumbnailGenerated(long j, String str, String str2);
    }

    private class ThumbnailHandler extends Handler {
        private ThumbnailHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            ThumbnailGenerator.this.generateThumbnail((ThumbnailGenerationRequest) message.obj);
        }
    }

    ThumbnailGenerator(Context context, String str, Callback callback) {
        this.mCallback = callback;
        initWorkFolder(context, str);
        initWorkThread();
    }

    private void initWorkFolder(Context context, String str) {
        File file;
        if (str != null) {
            file = new File(str);
            if (file.exists() && file.isFile()) {
                throw new IllegalArgumentException("Matisse thumbnailDir is a file.");
            }
        }
        file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (file == null) {
            file = context.getCacheDir();
        }
        str = file.getAbsolutePath();
        this.mWorkFolder = new File(str, THUMBNAIL_WORK_FOLDER_NAME);
        if (this.mWorkFolder.exists() && this.mWorkFolder.isFile()) {
            this.mWorkFolder.delete();
        }
        if (!this.mWorkFolder.exists()) {
            this.mWorkFolder.mkdirs();
        }
    }

    private void initWorkThread() {
        HandlerThread handlerThread = new HandlerThread("ThumbnailGenerator");
        handlerThread.start();
        this.mThumbnailLooper = handlerThread.getLooper();
        this.mThumbnailHandler = new ThumbnailHandler(this.mThumbnailLooper);
    }

    void postGenerationRequest(ThumbnailGenerationRequest thumbnailGenerationRequest) {
        Message obtainMessage = this.mThumbnailHandler.obtainMessage();
        obtainMessage.obj = thumbnailGenerationRequest;
        this.mThumbnailHandler.sendMessage(obtainMessage);
    }

    @WorkerThread
    private void generateThumbnail(ThumbnailGenerationRequest thumbnailGenerationRequest) {
        Bitmap videoThumbnail;
        if (thumbnailGenerationRequest.isVideo) {
            videoThumbnail = ThumbnailGeneratorHelper.getVideoThumbnail(thumbnailGenerationRequest.orig_path, 300, 300, 1);
        } else {
            videoThumbnail = ThumbnailGeneratorHelper.getImageThumbnail(thumbnailGenerationRequest.orig_path, 300, 300);
        }
        String str = null;
        if (videoThumbnail != null) {
            str = saveThumbnail(videoThumbnail, generateThumbnailName(thumbnailGenerationRequest));
            videoThumbnail.recycle();
        }
        this.mCallback.onThumbnailGenerated(thumbnailGenerationRequest.orig_id, thumbnailGenerationRequest.orig_path, str);
    }

    private static String generateThumbnailName(ThumbnailGenerationRequest thumbnailGenerationRequest) {
        return (thumbnailGenerationRequest.orig_id + "_" + thumbnailGenerationRequest.orig_path).hashCode() + ".jpg";
    }

    private String saveThumbnail(Bitmap bitmap, String str) {
        File file = new File(this.mWorkFolder, str);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    void destroy() {
        this.mThumbnailLooper.quit();
    }
}
