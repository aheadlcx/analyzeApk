package com.sensetime.stmobile;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class STSoundPlay {
    private static String TAG = "STSoundPlay";
    private final String CACHED_FOLDER = "Audio";
    OnAudioFocusChangeListener afChangeListener = new OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            if (i == -2) {
                Log.e(STSoundPlay.TAG, "AUDIOFOCUS_LOSS_TRANSIENT reset");
                if (STSoundPlay.this.mediaPlayer.isPlaying()) {
                    STSoundPlay.this.mediaPlayer.pause();
                }
            } else if (i == 1) {
                Log.e(STSoundPlay.TAG, "AUDIOFOCUS_GAIN");
                if (STSoundPlay.this.mediaPlayer != null && !STSoundPlay.this.mediaPlayer.isPlaying()) {
                    STSoundPlay.this.mediaPlayer.start();
                }
            } else if (i == -1) {
                Log.e(STSoundPlay.TAG, "AUDIOFOCUS_LOSS reset");
            }
        }
    };
    private AudioManager mAudioManager;
    private String mCachedPath;
    private OnCompletionListener mCompletionListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            try {
                SoundMetaData soundMetaData = (SoundMetaData) STSoundPlay.this.mSoundMetaDataMap.get(STSoundPlay.this.mCurrentPlaying);
                if (soundMetaData != null) {
                    int i = soundMetaData.loop - 1;
                    soundMetaData.loop = i;
                    if (i > 0) {
                        Log.e(STSoundPlay.TAG, "loop " + soundMetaData.loop);
                        STSoundPlay.this.mediaPlayer.start();
                        return;
                    }
                }
                Log.e(STSoundPlay.TAG, "play done");
                STSoundPlay.this.setSoundPlayDone(soundMetaData.name);
                STSoundPlay.this.mediaPlayer.stop();
                STSoundPlay.this.mediaPlayer.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private Context mContext;
    private String mCurrentPlaying;
    private OnErrorListener mErrorListener = new OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Log.e(STSoundPlay.TAG, "MediaPlayer error: " + i + VoiceWakeuperAidl.PARAMS_SEPARATE + i2);
            return true;
        }
    };
    private PlayControlListener mPlayControlDefaultListener = new PlayControlListener() {
        public void onSoundLoaded(String str, byte[] bArr) {
            if (str != null) {
                Log.e(STSoundPlay.TAG, "onSoundLoaded " + str);
                String access$400 = STSoundPlay.this.saveSoundToFile(str, bArr);
                if (access$400 != null) {
                    SoundMetaData soundMetaData = (SoundMetaData) STSoundPlay.this.mSoundMetaDataMap.get(str);
                    if (soundMetaData == null) {
                        soundMetaData = new SoundMetaData();
                    }
                    soundMetaData.cachePath = access$400;
                    soundMetaData.name = str;
                    STSoundPlay.this.mSoundMetaDataMap.put(str, soundMetaData);
                    return;
                }
                Log.e(STSoundPlay.TAG, "SoundFilePath is null");
            }
        }

        public void onStartPlay(String str, int i) {
            SoundMetaData soundMetaData = (SoundMetaData) STSoundPlay.this.mSoundMetaDataMap.get(str);
            if (soundMetaData == null) {
                Log.e(STSoundPlay.TAG, "No meta-data when start");
                return;
            }
            soundMetaData.loop = i;
            Log.e(STSoundPlay.TAG, "onStartPlay " + str);
            if (STSoundPlay.this.mediaPlayer.isPlaying()) {
                Log.e(STSoundPlay.TAG, "Stop it before play");
                STSoundPlay.this.mediaPlayer.reset();
            }
            try {
                STSoundPlay.this.mediaPlayer.setDataSource(STSoundPlay.this.mCachedPath + File.separator + str);
                STSoundPlay.this.mediaPlayer.prepare();
            } catch (IOException e) {
                Log.e(STSoundPlay.TAG, "IOException:" + e.toString());
                e.printStackTrace();
            } catch (IllegalStateException e2) {
                Log.e(STSoundPlay.TAG, "IllegalStateException:" + e2.toString());
                e2.printStackTrace();
            }
            STSoundPlay.this.mCurrentPlaying = str;
            if (i == 0) {
                STSoundPlay.this.mediaPlayer.setLooping(true);
            }
            STSoundPlay.this.mediaPlayer.start();
        }

        public void onStopPlay(String str) {
            Log.e(STSoundPlay.TAG, "onStopPlay " + str);
            if (((SoundMetaData) STSoundPlay.this.mSoundMetaDataMap.get(str)) == null) {
                Log.e(STSoundPlay.TAG, "No meta-data when stop");
            } else if (STSoundPlay.this.mediaPlayer.isPlaying()) {
                Log.e(STSoundPlay.TAG, "Playing when onStopPlay callback");
                STSoundPlay.this.mediaPlayer.reset();
            }
        }
    };
    private HashMap<String, SoundMetaData> mSoundMetaDataMap = new HashMap();
    private MediaPlayer mediaPlayer;
    private WeakReference<STMobileStickerNative> stickerHandleRef;

    public interface PlayControlListener {
        void onSoundLoaded(String str, byte[] bArr);

        void onStartPlay(String str, int i);

        void onStopPlay(String str);
    }

    private static class SoundMetaData {
        String cachePath;
        int loop;
        String name;

        private SoundMetaData() {
        }
    }

    public STSoundPlay(Context context) {
        this.mContext = context.getApplicationContext();
        this.mCachedPath = this.mContext.getExternalCacheDir() + File.separator + "Audio";
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        File file = new File(this.mCachedPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        initMediaPlayer();
    }

    public void setStickHandle(STMobileStickerNative sTMobileStickerNative) {
        this.stickerHandleRef = new WeakReference(sTMobileStickerNative);
    }

    public void release() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        if (this.mAudioManager != null) {
            this.mAudioManager.abandonAudioFocus(this.afChangeListener);
        }
    }

    public void setPlayControlListener(PlayControlListener playControlListener) {
        if (playControlListener != null) {
            this.mPlayControlDefaultListener = playControlListener;
        }
    }

    public void setSoundPlayDone(String str) {
        if (this.stickerHandleRef.get() != null) {
            ((STMobileStickerNative) this.stickerHandleRef.get()).setSoundPlayDone(str);
        }
    }

    private void onSoundLoaded(String str, byte[] bArr) {
        if (this.mPlayControlDefaultListener != null) {
            this.mPlayControlDefaultListener.onSoundLoaded(str, bArr);
        }
    }

    private void onStartPlay(String str, int i) {
        if (this.mPlayControlDefaultListener != null) {
            this.mPlayControlDefaultListener.onStartPlay(str, i);
        }
    }

    private void onStopPlay(String str) {
        if (this.mPlayControlDefaultListener != null) {
            this.mPlayControlDefaultListener.onStopPlay(str);
        }
    }

    private void initMediaPlayer() {
        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.setOnCompletionListener(this.mCompletionListener);
        this.mediaPlayer.setOnErrorListener(this.mErrorListener);
        this.mediaPlayer.reset();
    }

    private boolean requestFocus() {
        if (this.mAudioManager.requestAudioFocus(this.afChangeListener, 3, 2) == 1) {
            return true;
        }
        return false;
    }

    private String saveSoundToFile(String str, byte[] bArr) {
        File file = new File(this.mCachedPath);
        boolean z = true;
        if (!file.exists()) {
            z = file.mkdirs();
        }
        if (z) {
            File file2;
            try {
                file2 = new File(file.getPath() + File.separator + str);
                FileOutputStream fileOutputStream = new FileOutputStream(file2, false);
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "write file failed:" + e.toString());
                file2 = null;
            }
            if (file2 != null) {
                return file2.getAbsolutePath();
            }
            return null;
        }
        Log.e(TAG, this.mCachedPath + " is not exist");
        return null;
    }
}
