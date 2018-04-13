package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ali.auth.third.core.model.Constants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.j;
import com.umeng.analytics.MobclickAgent;
import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;

public class s extends a<ListItemObject> {
    private TextView e;
    private AsyncImageView f;
    private TextView g;
    private AsyncImageView h;
    private TextView i;
    private ImageButton j;
    private LinearLayout k;
    private TextView l;

    public s(Context context, b bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.new_new_list_item_user_info, viewGroup);
        this.e = (TextView) inflate.findViewById(R.id.user_v);
        this.f = (AsyncImageView) inflate.findViewById(R.id.writerProfile);
        this.k = (LinearLayout) inflate.findViewById(R.id.name_time_layout);
        this.g = (TextView) inflate.findViewById(R.id.writerName);
        this.h = (AsyncImageView) inflate.findViewById(R.id.iv_members_mark);
        this.i = (TextView) inflate.findViewById(R.id.addtime);
        this.l = (TextView) inflate.findViewById(R.id.nearby);
        this.j = (ImageButton) inflate.findViewById(R.id.itemMore);
        return inflate;
    }

    public void c() {
        if (TextUtils.isEmpty(((ListItemObject) this.c).getSina_v()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(((ListItemObject) this.c).getSina_v())) {
            this.e.setVisibility(8);
        } else {
            this.e.setVisibility(0);
        }
        this.f.setPostAvatarImage(((ListItemObject) this.c).getProfile());
        this.g.setText(((ListItemObject) this.c).getName());
        if (TextUtils.isEmpty(((ListItemObject) this.c).getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(((ListItemObject) this.c).getIs_vip())) {
            this.g.setTextColor(this.a.getResources().getColor(R.color.item_user_name_color));
            this.h.setVisibility(8);
        } else {
            this.g.setTextColor(this.a.getResources().getColor(j.H));
            try {
                this.h.setVisibility(0);
                Drawable gifDrawable = new GifDrawable(this.a.getResources(), j.I);
                this.h.setImageDrawable(gifDrawable);
                gifDrawable.start();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        CharSequence addtime = ((ListItemObject) this.c).getAddtime();
        if (!TextUtils.isEmpty(addtime)) {
            this.i.setText(addtime);
        }
        if (TextUtils.isEmpty(((ListItemObject) this.c).getLength())) {
            this.l.setVisibility(8);
        } else {
            this.l.setText(((ListItemObject) this.c).getLength());
            this.l.setVisibility(0);
        }
        this.f.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.h.setOnClickListener(this);
        if (this.b.e != 14) {
            this.j.setVisibility(8);
            this.j.setOnClickListener(this);
            return;
        }
        this.j.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.writerProfile:
            case R.id.name_time_layout:
                if (!((ListItemObject) this.c).getAddtime().startsWith("待审内容")) {
                    this.b.c.a(view, (ListItemObject) this.c, "");
                    return;
                }
                return;
            case R.id.itemMore:
                MobclickAgent.onEvent(this.a, "E01_A10", "帖子右上角三个点点击");
                this.b.c.c(view, (ListItemObject) this.c);
                return;
            default:
                return;
        }
    }
}
