package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.umeng.analytics.MobclickAgent;

public class o extends a<ListItemObject> implements OnLongClickListener {
    public RelativeLayout e;
    public AsyncImageView f;
    private Handler g;

    public o(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_rich_text_layout, viewGroup);
        this.e = (RelativeLayout) inflate.findViewById(R.id.ll_content);
        this.e.getLayoutParams().height = ((com.budejie.www.adapter.b.a.a - com.budejie.www.adapter.b.a.d) * 318) / 594;
        this.f = (AsyncImageView) inflate.findViewById(R.id.content_image_url);
        return inflate;
    }

    public void c() {
        this.e.setOnClickListener(this);
        this.e.setOnLongClickListener(this);
        this.f.setPostImage(((ListItemObject) this.c).getRichObject().getImgUrl());
    }

    public void onClick(final View view) {
        if (this.g == null) {
            this.g = new Handler(this) {
                final /* synthetic */ o b;

                public void handleMessage(Message message) {
                    if (message.what == 1) {
                        this.b.b.c.e(view, (ListItemObject) this.b.c);
                    } else if (this.b.b.f.a != null) {
                        this.b.b.f.a.performClick();
                        MobclickAgent.onEvent(this.b.a, this.b.a.getString(R.string.double_click_praise), this.b.a.getString(R.string.double_click_praise_describe));
                    }
                }
            };
        }
        if (this.g.hasMessages(1)) {
            this.g.removeMessages(1);
            this.g.sendEmptyMessage(2);
            return;
        }
        this.g.sendEmptyMessageDelayed(1, 0);
    }

    public boolean onLongClick(View view) {
        if (this.b.f.b != null) {
            this.b.f.b.performClick();
        }
        return true;
    }
}
