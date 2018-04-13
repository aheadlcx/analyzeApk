package com.meizu.cloud.pushsdk.networking.common;

import com.meizu.cloud.pushsdk.networking.core.Core;
import com.meizu.cloud.pushsdk.networking.interfaces.ConnectionQualityChangeListener;

public class ConnectionClassManager {
    private static final long BANDWIDTH_LOWER_BOUND = 10;
    private static final int BYTES_TO_BITS = 8;
    private static final int DEFAULT_GOOD_BANDWIDTH = 2000;
    private static final int DEFAULT_MODERATE_BANDWIDTH = 550;
    private static final int DEFAULT_POOR_BANDWIDTH = 150;
    private static final int DEFAULT_SAMPLES_TO_QUALITY_CHANGE = 5;
    private static final int MINIMUM_SAMPLES_TO_DECIDE_QUALITY = 2;
    private static ConnectionClassManager sInstance;
    private ConnectionQualityChangeListener mConnectionQualityChangeListener;
    private int mCurrentBandwidth = 0;
    private int mCurrentBandwidthForSampling = 0;
    private ConnectionQuality mCurrentConnectionQuality = ConnectionQuality.UNKNOWN;
    private int mCurrentNumberOfSample = 0;

    public static ConnectionClassManager getInstance() {
        if (sInstance == null) {
            synchronized (ConnectionClassManager.class) {
                if (sInstance == null) {
                    sInstance = new ConnectionClassManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized void updateBandwidth(long j, long j2) {
        if (j2 != 0 && j >= 20000 && ((((double) j) * 1.0d) / ((double) j2)) * 8.0d >= 10.0d) {
            this.mCurrentBandwidthForSampling = (int) (((((((double) j) * 1.0d) / ((double) j2)) * 8.0d) + ((double) (this.mCurrentBandwidthForSampling * this.mCurrentNumberOfSample))) / ((double) (this.mCurrentNumberOfSample + 1)));
            this.mCurrentNumberOfSample++;
            if (this.mCurrentNumberOfSample == 5 || (this.mCurrentConnectionQuality == ConnectionQuality.UNKNOWN && this.mCurrentNumberOfSample == 2)) {
                ConnectionQuality connectionQuality = this.mCurrentConnectionQuality;
                this.mCurrentBandwidth = this.mCurrentBandwidthForSampling;
                if (this.mCurrentBandwidthForSampling <= 0) {
                    this.mCurrentConnectionQuality = ConnectionQuality.UNKNOWN;
                } else if (this.mCurrentBandwidthForSampling < 150) {
                    this.mCurrentConnectionQuality = ConnectionQuality.POOR;
                } else if (this.mCurrentBandwidthForSampling < DEFAULT_MODERATE_BANDWIDTH) {
                    this.mCurrentConnectionQuality = ConnectionQuality.MODERATE;
                } else if (this.mCurrentBandwidthForSampling < 2000) {
                    this.mCurrentConnectionQuality = ConnectionQuality.GOOD;
                } else if (this.mCurrentBandwidthForSampling > 2000) {
                    this.mCurrentConnectionQuality = ConnectionQuality.EXCELLENT;
                }
                if (this.mCurrentNumberOfSample == 5) {
                    this.mCurrentBandwidthForSampling = 0;
                    this.mCurrentNumberOfSample = 0;
                }
                if (!(this.mCurrentConnectionQuality == connectionQuality || this.mConnectionQualityChangeListener == null)) {
                    Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() {
                        public void run() {
                            ConnectionClassManager.this.mConnectionQualityChangeListener.onChange(ConnectionClassManager.this.mCurrentConnectionQuality, ConnectionClassManager.this.mCurrentBandwidth);
                        }
                    });
                }
            }
        }
    }

    public int getCurrentBandwidth() {
        return this.mCurrentBandwidth;
    }

    public ConnectionQuality getCurrentConnectionQuality() {
        return this.mCurrentConnectionQuality;
    }

    public void setListener(ConnectionQualityChangeListener connectionQualityChangeListener) {
        this.mConnectionQualityChangeListener = connectionQualityChangeListener;
    }

    public void removeListener() {
        this.mConnectionQualityChangeListener = null;
    }

    public static void shutDown() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
