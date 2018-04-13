package com.r0adkll.slidr.model;

import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;

public class SlidrConfig {
    private int colorPrimary;
    private int colorSecondary;
    private float distanceThreshold;
    private boolean edgeOnly;
    private float edgeSize;
    private SlidrListener listener;
    private SlidrPosition position;
    private int scrimColor;
    private float scrimEndAlpha;
    private float scrimStartAlpha;
    private float sensitivity;
    private float touchSize;
    private float velocityThreshold;

    public static class Builder {
        private SlidrConfig config = new SlidrConfig();

        public Builder primaryColor(@ColorInt int i) {
            this.config.colorPrimary = i;
            return this;
        }

        public Builder secondaryColor(@ColorInt int i) {
            this.config.colorSecondary = i;
            return this;
        }

        public Builder position(SlidrPosition slidrPosition) {
            this.config.position = slidrPosition;
            return this;
        }

        public Builder touchSize(float f) {
            this.config.touchSize = f;
            return this;
        }

        public Builder sensitivity(float f) {
            this.config.sensitivity = f;
            return this;
        }

        public Builder scrimColor(@ColorInt int i) {
            this.config.scrimColor = i;
            return this;
        }

        public Builder scrimStartAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
            this.config.scrimStartAlpha = f;
            return this;
        }

        public Builder scrimEndAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
            this.config.scrimEndAlpha = f;
            return this;
        }

        public Builder velocityThreshold(float f) {
            this.config.velocityThreshold = f;
            return this;
        }

        public Builder distanceThreshold(@FloatRange(from = 0.10000000149011612d, to = 0.8999999761581421d) float f) {
            this.config.distanceThreshold = f;
            return this;
        }

        public Builder edge(boolean z) {
            this.config.edgeOnly = z;
            return this;
        }

        public Builder edgeSize(@FloatRange(from = 0.0d, to = 1.0d) float f) {
            this.config.edgeSize = f;
            return this;
        }

        public Builder listener(SlidrListener slidrListener) {
            this.config.listener = slidrListener;
            return this;
        }

        public SlidrConfig build() {
            return this.config;
        }
    }

    private SlidrConfig() {
        this.colorPrimary = -1;
        this.colorSecondary = -1;
        this.touchSize = -1.0f;
        this.sensitivity = 1.0f;
        this.scrimColor = -16777216;
        this.scrimStartAlpha = 0.8f;
        this.scrimEndAlpha = 0.0f;
        this.velocityThreshold = 5.0f;
        this.distanceThreshold = 0.25f;
        this.edgeOnly = false;
        this.edgeSize = 0.18f;
        this.position = SlidrPosition.LEFT;
    }

    public int getPrimaryColor() {
        return this.colorPrimary;
    }

    public int getSecondaryColor() {
        return this.colorSecondary;
    }

    public int getScrimColor() {
        return this.scrimColor;
    }

    public float getScrimStartAlpha() {
        return this.scrimStartAlpha;
    }

    public float getScrimEndAlpha() {
        return this.scrimEndAlpha;
    }

    public SlidrPosition getPosition() {
        return this.position;
    }

    public float getTouchSize() {
        return this.touchSize;
    }

    public float getVelocityThreshold() {
        return this.velocityThreshold;
    }

    public float getDistanceThreshold() {
        return this.distanceThreshold;
    }

    public float getSensitivity() {
        return this.sensitivity;
    }

    public SlidrListener getListener() {
        return this.listener;
    }

    public boolean areStatusBarColorsValid() {
        return (this.colorPrimary == -1 || this.colorSecondary == -1) ? false : true;
    }

    public boolean isEdgeOnly() {
        return this.edgeOnly;
    }

    public float getEdgeSize(float f) {
        return this.edgeSize * f;
    }

    public void setColorPrimary(int i) {
        this.colorPrimary = i;
    }

    public void setColorSecondary(int i) {
        this.colorSecondary = i;
    }

    public void setTouchSize(float f) {
        this.touchSize = f;
    }

    public void setSensitivity(float f) {
        this.sensitivity = f;
    }

    public void setScrimColor(int i) {
        this.scrimColor = i;
    }

    public void setScrimStartAlpha(float f) {
        this.scrimStartAlpha = f;
    }

    public void setScrimEndAlpha(float f) {
        this.scrimEndAlpha = f;
    }

    public void setVelocityThreshold(float f) {
        this.velocityThreshold = f;
    }

    public void setDistanceThreshold(float f) {
        this.distanceThreshold = f;
    }
}
