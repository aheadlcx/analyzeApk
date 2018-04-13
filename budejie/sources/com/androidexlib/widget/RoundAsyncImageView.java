package com.androidexlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.androidexlib.widget.asyncimage.AsyncImageView;

public class RoundAsyncImageView extends AsyncImageView {
    private int d = 0;
    private Context e;
    private int f = -1;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;

    public void setBorderThickness(int i) {
        this.d = i;
    }

    public void setBorderOutsideColor(int i) {
        this.g = i;
    }

    public void setBorderInsideColor(int i) {
        this.h = i;
    }

    public RoundAsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
        setCustomAttributes(attributeSet);
    }

    public RoundAsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        setCustomAttributes(attributeSet);
    }

    private void setCustomAttributes(AttributeSet attributeSet) {
    }
}
