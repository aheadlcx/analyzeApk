package com.facebook.imagepipeline.core;

public class ImagePipelineConfig$DefaultImageRequestConfig {
    private boolean mProgressiveRenderingEnabled;

    private ImagePipelineConfig$DefaultImageRequestConfig() {
        this.mProgressiveRenderingEnabled = false;
    }

    public void setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
    }

    public boolean isProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }
}
