package qsbk.app.widget;

import android.content.Context;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.widget.QiushiImageLayout.ViewFactory;

class dn implements ViewFactory<QBImageView> {
    final /* synthetic */ QiushiImageLayout a;

    dn(QiushiImageLayout qiushiImageLayout) {
        this.a = qiushiImageLayout;
    }

    public QBImageView createView(Context context, int i) {
        return new QBImageView(context);
    }

    public void onViewBind(QBImageView qBImageView, ImageInfo imageInfo, int i) {
        int i2 = 0;
        String str = "";
        if (imageInfo != null) {
            int formatTagImage = MediaFormat.getFormatTagImage(imageInfo.mediaFormat);
            qBImageView.setVisibility(0);
            String imageUrl = imageInfo.getImageUrl();
            if (imageInfo.mediaFormat == MediaFormat.IMAGE_LONG) {
                ((GenericDraweeHierarchy) qBImageView.getHierarchy()).setActualImageScaleType(FrescoImageloader.SCALE_CENTER_TOP);
                str = imageUrl;
                i2 = formatTagImage;
            } else {
                ((GenericDraweeHierarchy) qBImageView.getHierarchy()).setActualImageScaleType(ScaleType.CENTER_CROP);
                str = imageUrl;
                i2 = formatTagImage;
            }
        } else {
            qBImageView.setVisibility(8);
        }
        FrescoImageloader.displayImage(qBImageView, str, TileBackground.getBackgroud(this.a.getContext(), BgImageType.ARTICLE));
        qBImageView.setTypeImageResouce(i2);
        qBImageView.setOnClickListener(new do(this, qBImageView, i));
    }
}
