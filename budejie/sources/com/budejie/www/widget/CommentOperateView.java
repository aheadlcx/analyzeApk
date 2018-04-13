package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.util.i;

public class CommentOperateView extends RelativeLayout implements OnClickListener {
    private final String a = "CommentOperateView";
    private Context b;
    private TextView c;
    private TextView d;
    private TextView e;
    private a f;
    private boolean g;

    public interface a {
        void a();

        void b();

        void c();
    }

    public void setOperateListenr(a aVar) {
        this.f = aVar;
    }

    public void setPostType(boolean z) {
        this.g = z;
        if (z) {
            this.e.setVisibility(0);
            this.e.setOnClickListener(this);
            return;
        }
        this.e.setVisibility(4);
    }

    public CommentOperateView(Context context) {
        super(context);
        this.b = context;
        a();
    }

    public CommentOperateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        a();
    }

    public CommentOperateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        a();
    }

    public void a() {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.comment_opreate_layout, null);
        addView(inflate, new LayoutParams(-1, (int) (((float) R$styleable.Theme_Custom_top_navigation_middle) * i.a().b(this.b))));
        this.c = (TextView) inflate.findViewById(R.id.comment_operate_album);
        this.d = (TextView) inflate.findViewById(R.id.comment_operate_vote);
        this.e = (TextView) inflate.findViewById(R.id.comment_operate_dub);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.f != null) {
            switch (view.getId()) {
                case R.id.comment_operate_album:
                    this.f.a();
                    return;
                case R.id.comment_operate_vote:
                    this.f.b();
                    return;
                case R.id.comment_operate_dub:
                    if (this.g) {
                        this.f.c();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
