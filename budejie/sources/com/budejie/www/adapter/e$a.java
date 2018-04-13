package com.budejie.www.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;

public class e$a extends ViewHolder {
    AsyncImageView a;
    TextView b;
    RelativeLayout c;
    RelativeLayout d;
    TextView e;
    TextView f;
    TextView g;
    final /* synthetic */ e h;

    public e$a(e eVar, View view) {
        this.h = eVar;
        super(view);
        this.a = (AsyncImageView) view.findViewById(R.id.async_image_view);
        this.b = (TextView) view.findViewById(R.id.comment_text_view);
        this.c = (RelativeLayout) view.findViewById(R.id.comment_layout);
        this.d = (RelativeLayout) view.findViewById(R.id.video_duration_layout);
        this.e = (TextView) view.findViewById(R.id.long_image_textView);
        this.f = (TextView) view.findViewById(R.id.title_textView);
        this.g = (TextView) view.findViewById(R.id.video_duration_text_view);
    }
}
