package com.facebook.drawee.backends.pipeline;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;

class PipelineDraweeController$1 implements DrawableFactory {
    final /* synthetic */ PipelineDraweeController this$0;

    PipelineDraweeController$1(PipelineDraweeController pipelineDraweeController) {
        this.this$0 = pipelineDraweeController;
    }

    public boolean supportsImageType(CloseableImage closeableImage) {
        return true;
    }

    public Drawable createDrawable(CloseableImage closeableImage) {
        if (closeableImage instanceof CloseableStaticBitmap) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            Drawable bitmapDrawable = new BitmapDrawable(PipelineDraweeController.access$000(this.this$0), closeableStaticBitmap.getUnderlyingBitmap());
            if (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) {
                return bitmapDrawable;
            }
            return new OrientedDrawable(bitmapDrawable, closeableStaticBitmap.getRotationAngle());
        } else if (PipelineDraweeController.access$100(this.this$0) == null || !PipelineDraweeController.access$100(this.this$0).supportsImageType(closeableImage)) {
            return null;
        } else {
            return PipelineDraweeController.access$100(this.this$0).createDrawable(closeableImage);
        }
    }
}
