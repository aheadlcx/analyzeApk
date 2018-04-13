package com.budejie.www.goddubbing.wediget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.budejie.www.R;
import com.budejie.www.util.an;

public class VideoLayout extends RelativeLayout {
    private Context a;
    private View b;
    private VideoView c;
    private ImageView d;
    private ImageView e;

    public VideoLayout(Context context) {
        this(context, null);
    }

    public VideoLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        a();
    }

    private void a() {
        this.b = LayoutInflater.from(this.a).inflate(R.layout.god_dubbing_video_layout_view, null);
        this.c = (VideoView) this.b.findViewById(R.id.video_view);
        this.d = (ImageView) this.b.findViewById(R.id.preview_image_view);
        this.e = (ImageView) this.b.findViewById(R.id.default_image_view);
        addView(this.b);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        LayoutParams layoutParams = this.b.getLayoutParams();
        int x = an.x(this.a);
        layoutParams.height = x;
        layoutParams.width = x;
        this.b.setLayoutParams(layoutParams);
    }

    public VideoView getVideoView() {
        return this.c;
    }

    public ImageView getDefaultImageView() {
        return this.e;
    }

    public ImageView getPreviewImageView() {
        return this.d;
    }
}
