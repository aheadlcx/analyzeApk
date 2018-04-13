package com.sprite.ads.third;

import android.support.annotation.Nullable;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.NativeAdData;
import java.util.ArrayList;

public class ThirdNativeAdPool extends ArrayList<NativeAdData> {
    private int adIndex = 0;
    private RefreshListener mListener;

    public interface RefreshListener {
        void refreshData();
    }

    public void clear() {
        this.adIndex = 0;
        super.clear();
    }

    @Nullable
    public NativeAdData getNativeAdData() {
        ADLog.d("广告个数：" + size() + " 当前索引：" + this.adIndex);
        if (size() == 0) {
            this.adIndex = 0;
            return null;
        } else if (this.adIndex < size()) {
            int i = this.adIndex;
            this.adIndex = i + 1;
            return (NativeAdData) get(i);
        } else {
            if (this.mListener != null) {
                this.mListener.refreshData();
            }
            this.adIndex = 0;
            return (NativeAdData) get(this.adIndex);
        }
    }

    public boolean isLastAd() {
        return this.adIndex >= size();
    }

    public void removeCurrentAd() {
        if (size() <= 0) {
            return;
        }
        if (this.adIndex > 0) {
            int i = this.adIndex - 1;
            this.adIndex = i;
            remove(i);
            return;
        }
        remove(0);
    }

    public void setListener(RefreshListener refreshListener) {
        this.mListener = refreshListener;
    }
}
