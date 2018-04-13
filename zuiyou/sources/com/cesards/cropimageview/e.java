package com.cesards.cropimageview;

import android.graphics.Matrix;
import com.cesards.cropimageview.CropImageView.CropType;

public class e extends b implements c {
    private Matrix b;

    public e(CropImageView cropImageView) {
        super(cropImageView);
        a(cropImageView);
    }

    private void a(CropImageView cropImageView) {
        if (cropImageView.getCropType() != CropType.NONE) {
            this.b = new Matrix();
        }
    }

    public Matrix a() {
        return this.b == null ? this.a.getImageMatrix() : this.b;
    }
}
