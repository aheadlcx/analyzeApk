package cn.xiaochuan.media.av;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.widget.FrameLayout;
import cn.xiaochuan.media.av.XPlayer.XPlayerObserver;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XPlayerView extends FrameLayout implements SurfaceTextureListener, XPlayerObserver {
    private static final String HASH_ALGORITHM = "MD5";
    private static final int RADIX = 36;
    private static final String TAG = "XKPlayer";
    private String mCacheDir;
    private boolean mDownloadSuc = false;
    private boolean mEnableRotate = true;
    private String mFinalPath;
    private boolean mLoop = true;
    private XKPlayerObserver mObserver;
    private XPlayer mPlayer;
    private boolean mSeekExact = false;
    private boolean mSupportPlayProgress = false;
    private String mTempPath;
    private TextureView mTextureView;
    private String mUrl;
    private String mVFilter;
    private float mVolume = 1.0f;

    public interface XKPlayerObserver {
        void onDownloadProgress(double d);

        void onPlayBeginRend();

        void onPlayLoop();

        void onPlayPause();

        void onPlayProgress(double d);

        void onPlayResume();

        void onPlayStart();

        void onPlayStop();
    }

    public XPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setCacheDir(context.getCacheDir().getAbsolutePath());
    }

    private void setCacheDir(String str) {
        this.mCacheDir = str;
        File file = new File(str);
        if (!(file.exists() && file.isDirectory())) {
            file.mkdir();
        }
        checkCacheSize(this.mCacheDir);
    }

    private void checkCacheSize(String str) {
        File file = new File(str);
        if (file != null && file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles.length > 50) {
                File file2 = null;
                for (int i = 0; i < listFiles.length; i++) {
                    if (file2 == null) {
                        file2 = listFiles[i];
                    } else if (listFiles[i].lastModified() < file2.lastModified()) {
                        file2 = listFiles[i];
                    }
                }
                if (file2 != null) {
                    file2.delete();
                }
            }
        }
    }

    public void setObserver(XKPlayerObserver xKPlayerObserver) {
        this.mObserver = xKPlayerObserver;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void play(String str) {
        this.mUrl = str;
        Log.i(TAG, String.format("play %s", new Object[]{this.mUrl}));
        if (this.mUrl != null && !this.mUrl.isEmpty()) {
            this.mDownloadSuc = false;
            if (!isUrl(this.mUrl)) {
                this.mFinalPath = this.mUrl;
                playLocalFile(this.mFinalPath);
            } else if (this.mCacheDir != null) {
                this.mFinalPath = this.mCacheDir + "/" + makeUrlKey(this.mUrl);
                File file = new File(this.mFinalPath);
                if (!file.exists()) {
                    this.mTempPath = this.mFinalPath + "_" + System.currentTimeMillis();
                    playLocalFile(String.format("cache:[%s]%s", new Object[]{this.mTempPath, this.mUrl}));
                    statistic_begin_download(this.mUrl);
                } else if (file.length() > 0) {
                    playLocalFile(this.mFinalPath);
                } else {
                    file.delete();
                    this.mTempPath = this.mFinalPath + "_" + System.currentTimeMillis();
                    playLocalFile(String.format("cache:[%s]%s", new Object[]{this.mTempPath, this.mUrl}));
                    statistic_begin_download(this.mUrl);
                }
            }
        }
    }

    public void pause() {
        if (this.mPlayer != null) {
            this.mPlayer.pause();
        }
    }

    public void seek(long j) {
        if (this.mPlayer != null) {
            this.mPlayer.seek(j);
        }
    }

    public void resume() {
        if (this.mPlayer != null) {
            this.mPlayer.resume();
        }
    }

    public void setVolume(float f) {
        this.mVolume = f;
        if (this.mPlayer != null) {
            this.mPlayer.setVolume(f);
        }
    }

    public void setLoop(boolean z) {
        this.mLoop = z;
        if (this.mPlayer != null) {
            this.mPlayer.setLoop(z);
        }
    }

    public void setSeekExact(boolean z) {
        this.mSeekExact = z;
        if (this.mPlayer != null) {
            this.mPlayer.setSeekExact(z);
        }
    }

    public void setEnableRotate(boolean z) {
        this.mEnableRotate = z;
        if (this.mPlayer != null) {
            this.mPlayer.setEnableRotate(z);
        }
    }

    public void setSupportPlayProgress(boolean z) {
        this.mSupportPlayProgress = z;
        if (this.mPlayer != null) {
            this.mPlayer.setSupportPlayProgress(z);
        }
    }

    public boolean isPlaying() {
        return this.mPlayer != null && this.mPlayer.isPlaying();
    }

    public boolean isPause() {
        return this.mPlayer != null && this.mPlayer.isPause();
    }

    public boolean isStop() {
        return this.mPlayer == null;
    }

    private static byte[] getMD5(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(HASH_ALGORITHM);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (NoSuchAlgorithmException e) {
        }
        return bArr2;
    }

    public static String makeUrlKey(String str) {
        return new BigInteger(getMD5(str.getBytes())).abs().toString(36);
    }

    private void playLocalFile(String str) {
        if (str == null) {
            Log.e(TAG, "playLocalFile path is null");
        } else if (!isPlaying() && !isPause()) {
            if (this.mTextureView != null) {
                this.mTextureView.setSurfaceTextureListener(null);
                removeView(this.mTextureView);
            }
            this.mTextureView = new TextureView(getContext());
            this.mTextureView.setSurfaceTextureListener(this);
            this.mTextureView.setAlpha(1.0f);
            addView(this.mTextureView);
            this.mPlayer = new XPlayer();
            this.mPlayer.setObserver(this);
            this.mPlayer.setTextureView(this.mTextureView);
            if (this.mVFilter != null) {
                this.mPlayer.setVFilters(this.mVFilter);
            }
            this.mPlayer.setPath(str);
            this.mPlayer.setVolume(this.mVolume);
            this.mPlayer.setLoop(this.mLoop);
            this.mPlayer.setSeekExact(this.mSeekExact);
            this.mPlayer.setEnableRotate(this.mEnableRotate);
            this.mPlayer.setSupportPlayProgress(this.mSupportPlayProgress);
            this.mPlayer.start();
        }
    }

    private boolean isUrl(String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.indexOf("http://") == 0 || toLowerCase.indexOf("https://") == 0) {
            return true;
        }
        return false;
    }

    public void stop() {
        if (this.mPlayer != null) {
            this.mPlayer.stop();
            this.mPlayer = null;
        }
    }

    public void destroySurface() {
        if (this.mTextureView != null) {
            removeView(this.mTextureView);
            this.mTextureView = null;
        }
    }

    private void log(String str, String str2) {
        Log.i(str, str2);
    }

    public void onPlayerStop(XPlayer xPlayer) {
        if (this.mPlayer == null || this.mPlayer == xPlayer) {
            if (this.mObserver != null) {
                this.mObserver.onPlayStop();
            }
            if (this.mTempPath != null && this.mFinalPath != null) {
                File file = new File(this.mTempPath);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public void onPlayerBeginRend(XPlayer xPlayer) {
        if ((this.mPlayer == null || this.mPlayer == xPlayer) && this.mObserver != null) {
            this.mObserver.onPlayBeginRend();
        }
    }

    public void onDownloadProgress(double d, XPlayer xPlayer) {
        if ((this.mPlayer == null || this.mPlayer == xPlayer) && this.mObserver != null) {
            if (d >= 1.0d) {
                this.mDownloadSuc = true;
                statistic_finish_download(this.mUrl, new File(this.mTempPath).length());
            }
            this.mObserver.onDownloadProgress(d);
        }
    }

    public void onPlayerPause(XPlayer xPlayer) {
        if ((this.mPlayer == null || this.mPlayer == xPlayer) && this.mObserver != null) {
            this.mObserver.onPlayPause();
        }
    }

    public void onPlayerResume(XPlayer xPlayer) {
        if ((this.mPlayer == null || this.mPlayer == xPlayer) && this.mObserver != null) {
            this.mObserver.onPlayResume();
        }
    }

    public void onPlayProgress(double d, XPlayer xPlayer) {
        if ((this.mPlayer == null || this.mPlayer == xPlayer) && this.mObserver != null) {
            this.mObserver.onPlayProgress(d);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.e(TAG, String.format("XKPlayer onSurfaceTextureAvailable %d %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        if (this.mPlayer != null) {
            this.mPlayer.setSurfaceTextureAvailable(i, i2);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.e(TAG, String.format("XKPlayer onSurfaceTextureSizeChanged %d %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        if (this.mPlayer != null) {
            this.mPlayer.setSurfaceTextureAvailable(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.e(TAG, String.format("XKPlayer onSurfaceTextureDestroyed", new Object[0]));
        if (this.mPlayer != null) {
            this.mPlayer.setSurfaceTextureDestroyed();
        }
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void resetSurface() {
        this.mPlayer.setSurfaceTextureAvailable(this.mTextureView.getWidth(), this.mTextureView.getHeight());
    }

    public void onPlayerStart(XPlayer xPlayer) {
        if (this.mObserver != null) {
            this.mObserver.onPlayStart();
        }
    }

    public void onPlayerLoop(XPlayer xPlayer) {
        if (this.mObserver != null) {
            this.mObserver.onPlayLoop();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    public String getFinalPath() {
        return this.mFinalPath;
    }

    public boolean isDownloadFinish() {
        return this.mDownloadSuc;
    }

    private void statistic_begin_download(String str) {
    }

    private void statistic_finish_download(String str, long j) {
    }

    public boolean saveVideo() {
        if (this.mFinalPath == null) {
            return false;
        }
        File file = new File(this.mFinalPath);
        if (file.exists() && file.length() > 0) {
            return true;
        }
        if (this.mTempPath == null || !this.mDownloadSuc) {
            return false;
        }
        return new File(this.mFinalPath).exists();
    }

    public void setVFilter(String str) {
        this.mVFilter = str;
    }
}
