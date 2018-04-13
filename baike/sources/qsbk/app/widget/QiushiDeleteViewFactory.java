package qsbk.app.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.widget.QiushiImageLayout.ViewFactory;

public class QiushiDeleteViewFactory implements ViewFactory {
    OnViewOptionClickListener a;

    public interface OnViewOptionClickListener {
        void onDelete(View view, int i);

        void onViewClick(View view, ImageInfo imageInfo, int i);
    }

    public QiushiDeleteViewFactory(OnViewOptionClickListener onViewOptionClickListener) {
        this.a = onViewOptionClickListener;
    }

    public View createView(Context context, int i) {
        return LayoutInflater.from(context).inflate(R.layout.layout_qiushi_image, null);
    }

    public void onViewBind(View view, ImageInfo imageInfo, int i) {
        if (imageInfo != null) {
            view.setVisibility(0);
            QBImageView qBImageView = (QBImageView) view.findViewById(R.id.image);
            FrescoImageloader.displayImage(qBImageView, imageInfo.getImageUrl());
            qBImageView.setTypeImageResouce(MediaFormat.getFormatTagImage(imageInfo.mediaFormat));
            qBImageView.setOnClickListener(new dj(this, view, imageInfo, i));
            ((ImageView) view.findViewById(R.id.delete)).setOnClickListener(new dk(this, i));
            return;
        }
        view.setVisibility(8);
    }
}
