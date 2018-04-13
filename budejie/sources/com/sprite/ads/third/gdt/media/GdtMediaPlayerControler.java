package com.sprite.ads.third.gdt.media;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.util.AdError;
import com.sprite.ads.media.MediaListener;
import com.sprite.ads.media.MediaPlayerControler;

public class GdtMediaPlayerControler extends MediaPlayerControler<GdtMediaAdItem> {
    private MediaView mMediaView;
    private NativeMediaADData mNativeMediaADData;

    public void bindView(ViewGroup viewGroup) {
        this.mMediaView = new MediaView(viewGroup.getContext());
        this.mMediaView.setLayoutParams(new LayoutParams(-1, -2));
        viewGroup.addView(this.mMediaView);
    }

    public void setDataResoure(GdtMediaAdItem gdtMediaAdItem) {
        this.mNativeMediaADData = (NativeMediaADData) gdtMediaAdItem.getThirdMediaData();
        this.mNativeMediaADData.bindView(this.mMediaView, false);
    }

    public void setMediaListener(final MediaListener mediaListener) {
        this.mNativeMediaADData.setMediaListener(new com.qq.e.ads.nativ.MediaListener() {
            public void onVideoReady(long j) {
                mediaListener.onVideoReady();
            }

            public void onVideoStart() {
                mediaListener.onVideoStart();
            }

            public void onVideoPause() {
            }

            public void onVideoComplete() {
                mediaListener.onVideoComplete();
            }

            public void onVideoError(AdError adError) {
                mediaListener.onVideoError();
            }

            public void onReplayButtonClicked() {
                mediaListener.onVideoReplay();
            }

            public void onADButtonClicked() {
                mediaListener.onADClicked();
            }

            public void onFullScreenChanged(boolean z) {
            }
        });
    }

    public boolean isVideoAD() {
        return this.mNativeMediaADData.isVideoAD();
    }

    public void stop() {
        this.mNativeMediaADData.stop();
    }

    public void replay() {
    }

    public void play() {
        this.mNativeMediaADData.play();
    }

    public long getDuration() {
        return (long) this.mNativeMediaADData.getDuration();
    }

    public int getCurrentPosition() {
        return this.mNativeMediaADData.getCurrentPosition();
    }

    public boolean isPlaying() {
        return this.mNativeMediaADData.isPlaying();
    }

    public void release() {
    }

    public int getProgress() {
        return this.mNativeMediaADData.getProgress();
    }

    public void onClicked(View view) {
        this.mNativeMediaADData.onClicked(view);
    }

    public void onExposured(View view) {
        this.mNativeMediaADData.onExposured(view);
    }

    public void onPlay(View view) {
    }
}
