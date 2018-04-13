package com.budejie.www.adapter.d;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.c.c$a;
import com.budejie.www.adapter.f.h;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.h.c;
import com.budejie.www.util.j;
import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;

public class n extends a {
    protected final SuggestedFollowsListItem a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final int d;
    protected c$a e;

    public /* synthetic */ Object d() {
        return a();
    }

    public n(Activity activity, c$a c_a, SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        this.a = suggestedFollowsListItem;
        this.b = activity;
        this.c = LayoutInflater.from(activity);
        this.d = i;
        this.e = c_a;
    }

    public View b() {
        h hVar = new h();
        View inflate = this.c.inflate(R.layout.suggested_follows_item_layout, null);
        hVar.a = (RelativeLayout) inflate.findViewById(R.id.sfLayout);
        hVar.b = (AsyncImageView) inflate.findViewById(R.id.sfPictureIV);
        hVar.c = (TextView) inflate.findViewById(R.id.sfNickname);
        hVar.d = (AsyncImageView) inflate.findViewById(R.id.iv_members_mark);
        hVar.e = (TextView) inflate.findViewById(R.id.sfInfo);
        hVar.f = (TextView) inflate.findViewById(R.id.cancel_btn);
        hVar.g = (TextView) inflate.findViewById(R.id.add_btn);
        hVar.h = inflate.findViewById(R.id.divider_h_view1);
        hVar.i = (ImageView) inflate.findViewById(R.id.sfSocialIcon);
        hVar.j = (RelativeLayout) inflate.findViewById(R.id.sfContentLayout);
        inflate.setTag(hVar);
        return inflate;
    }

    public int c() {
        return RowType.TXT_ROW.ordinal();
    }

    public void a(b bVar) {
        h hVar = (h) bVar;
        CharSequence charSequence = this.a.screen_name;
        CharSequence charSequence2 = this.a.social_name;
        Object obj = this.a.fans_count;
        if (TextUtils.isEmpty(charSequence)) {
            hVar.c.setText("");
        } else {
            hVar.c.setText(charSequence);
        }
        if (this.a.is_vip) {
            hVar.c.setTextColor(this.b.getResources().getColor(j.H));
            try {
                hVar.d.setVisibility(0);
                Drawable gifDrawable = new GifDrawable(this.b.getResources(), j.I);
                hVar.d.setImageDrawable(gifDrawable);
                gifDrawable.start();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            hVar.c.setTextColor(this.b.getResources().getColor(R.color.sf_nickname_color));
            hVar.d.setVisibility(8);
        }
        if (this.a.mark == 1) {
            if (TextUtils.isEmpty(charSequence2)) {
                hVar.e.setText("");
            } else {
                hVar.e.setText(charSequence2);
            }
            if (this.a.plat_flag == 0) {
                hVar.i.setVisibility(0);
                hVar.i.setImageResource(c.a().b(R.attr.ic_follows_sinafriend));
            } else if (this.a.plat_flag == 1) {
                hVar.i.setVisibility(0);
                hVar.i.setImageResource(R.drawable.ic_follows_tencentfriend);
            }
        } else {
            if (TextUtils.isEmpty(obj)) {
                hVar.e.setText("");
            } else {
                hVar.e.setText(obj + "人关注");
            }
            hVar.i.setVisibility(8);
        }
        if (this.a.is_follow == 0) {
            hVar.g.setVisibility(0);
            hVar.f.setVisibility(8);
        } else if (this.a.is_follow == 1) {
            hVar.g.setVisibility(8);
            hVar.f.setVisibility(0);
        }
        hVar.b.setAsyncCacheImage(this.a.header, R.drawable.head_portrait);
        hVar.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.a(this.a.a, this.a.d);
            }
        });
        hVar.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.b(this.a.a, this.a.d);
            }
        });
        hVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.a(view, this.a.a);
            }
        });
        hVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.a(view, this.a.a);
            }
        });
        hVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
    }

    public SuggestedFollowsListItem a() {
        return this.a;
    }
}
