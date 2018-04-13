package com.facebook.drawee.drawable;

public interface Rounded {
    int getBorderColor();

    float getBorderWidth();

    float getPadding();

    float[] getRadii();

    boolean isCircle();

    void setBorder(int i, float f);

    void setCircle(boolean z);

    void setPadding(float f);

    void setRadii(float[] fArr);

    void setRadius(float f);
}
