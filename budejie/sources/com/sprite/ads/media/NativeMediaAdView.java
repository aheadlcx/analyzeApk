package com.sprite.ads.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.MediaAdItem;
import com.sprite.ads.nati.reporter.Reporter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class NativeMediaAdView extends RelativeLayout implements MediaPlayerControl, Reporter {
    private ADStatusChangeListner mADStatusChangeListner;
    private LinkedList<MediaAdItem> mMediaAdItems;
    private MediaAdapter mMediaAdapter;
    private MediaListener mMediaListener;
    private MediaPlayerControler mMediaPlayerControl;
    private NativeMediaADListener mNativeMediaADListener;

    public NativeMediaAdView(Context context) {
        this(context, null);
    }

    public NativeMediaAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeMediaAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMediaAdItems = new LinkedList<MediaAdItem>() {
            private final int capacity = 10;

            public boolean addAll(Collection<? extends MediaAdItem> collection) {
                while (size() > 10 - collection.size()) {
                    if (pollLast() == null) {
                        break;
                    }
                }
                return super.addAll(0, collection);
            }

            public boolean offer(MediaAdItem mediaAdItem) {
                return super.offerFirst(mediaAdItem);
            }
        };
    }

    private void bindAD(MediaAdItem mediaAdItem, MediaListener mediaListener) {
        MediaPlayerControler createMediaPlayerControler = MediaFactory.createMediaPlayerControler(mediaAdItem.getDataSourceType());
        if (createMediaPlayerControler != null) {
            bindAD(mediaAdItem, createMediaPlayerControler, mediaListener);
        }
    }

    private void bindAD(MediaAdItem mediaAdItem, MediaPlayerControler mediaPlayerControler, MediaListener mediaListener) {
        this.mMediaPlayerControl = mediaPlayerControler;
        mediaPlayerControler.bindView(this);
        mediaPlayerControler.setDataResoure(mediaAdItem);
        mediaPlayerControler.setMediaListener(mediaListener);
    }

    private boolean isBind() {
        return this.mMediaPlayerControl != null;
    }

    public int getCurrentPosition() {
        return this.mMediaPlayerControl.getCurrentPosition();
    }

    public long getDuration() {
        return this.mMediaPlayerControl.getDuration();
    }

    public int getProgress() {
        return this.mMediaPlayerControl.getProgress();
    }

    public boolean isPlaying() {
        return this.mMediaPlayerControl.isPlaying();
    }

    public boolean isVideoAD() {
        return this.mMediaPlayerControl.isVideoAD();
    }

    void loadAd(DataSourceType dataSourceType, AdItem adItem, ADConfig aDConfig) {
        this.mMediaAdapter = MediaFactory.createMediaAdapter(dataSourceType, adItem, aDConfig);
        if (this.mMediaAdapter != null) {
            this.mNativeMediaADListener = new NativeMediaADListener<MediaAdItem<?>>() {
                public void onADLoaded(List<MediaAdItem> list) {
                    if (list.size() > 0) {
                        NativeMediaAdView.this.mMediaAdItems.addAll(list);
                    }
                }

                public void onADStatusChanged(MediaAdItem<?> mediaAdItem) {
                    if (NativeMediaAdView.this.mADStatusChangeListner != null) {
                        NativeMediaAdView.this.mADStatusChangeListner.onADStatusChanged(mediaAdItem);
                    }
                }

                public void onNoAD(int i) {
                }
            };
            this.mMediaAdapter.loadAd(getContext(), this.mNativeMediaADListener);
        }
    }

    public void onClicked(View view) {
        this.mMediaPlayerControl.onClicked(view);
    }

    public void onExposured(View view) {
        this.mMediaPlayerControl.onExposured(view);
    }

    public void onPlay(View view) {
        this.mMediaPlayerControl.onPlay(view);
    }

    public void play() {
        this.mMediaPlayerControl.play();
    }

    public MediaAdItem refreshAd() {
        MediaAdItem mediaAdItem = (MediaAdItem) this.mMediaAdItems.poll();
        if (mediaAdItem != null) {
            if (isBind()) {
                this.mMediaPlayerControl.setDataResoure(mediaAdItem);
                this.mMediaPlayerControl.setMediaListener(this.mMediaListener);
            } else {
                bindAD(mediaAdItem, this.mMediaListener);
            }
        }
        if ((mediaAdItem == null || this.mMediaAdItems.size() <= 4) && this.mMediaAdapter != null) {
            this.mMediaAdapter.loadAd(getContext(), this.mNativeMediaADListener);
        }
        return mediaAdItem;
    }

    public void release() {
        this.mMediaPlayerControl.release();
    }

    public void replay() {
        this.mMediaPlayerControl.replay();
    }

    public void setADStatusChangeListner(ADStatusChangeListner aDStatusChangeListner) {
        this.mADStatusChangeListner = aDStatusChangeListner;
    }

    public void setMediaListener(MediaListener mediaListener) {
        this.mMediaListener = mediaListener;
    }

    public void stop() {
        this.mMediaPlayerControl.stop();
    }
}
