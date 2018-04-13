package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.data.MemberDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import com.izuiyou.a.a.b;

public class HollowNavigationBar extends FrameLayout {
    private ImageView a;
    private ImageView b;
    private TextView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;

    public HollowNavigationBar(@NonNull Context context) {
        super(context);
        a();
    }

    public HollowNavigationBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HollowNavigationBar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_hollow_navigation, this);
        this.a = (ImageView) findViewById(R.id.nav_title_icon_right);
        this.b = (ImageView) findViewById(R.id.nav_title_icon_left);
        this.c = (TextView) findViewById(R.id.nav_title);
        this.e = (ImageView) findViewById(R.id.nav_chat);
        this.d = (ImageView) findViewById(R.id.nav_option_icon);
        this.f = (ImageView) findViewById(R.id.nav_back_icon);
        b();
    }

    private void b() {
        findViewById(R.id.nav_root_view).setPadding(0, (int) getResources().getDimension(R.dimen.status_bar_height), 0, 0);
    }

    public void setTitleIconRight(int i) {
        this.a.setImageResource(i);
        this.a.setVisibility(0);
    }

    public void setTitleIconLeft(int i) {
        this.b.setImageResource(i);
        this.b.setVisibility(0);
    }

    public void a(String str, int i, OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
        this.c.setTextColor(i);
        this.c.setText(str);
        this.c.setVisibility(0);
    }

    public void a(String str, int i) {
        a(str, i, null);
    }

    public void setTitle(String str) {
        a(str, -1);
    }

    public void a(int i, OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
        this.d.setImageResource(i);
        this.d.setVisibility(0);
    }

    public void b(int i, OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
        this.f.setImageResource(i);
        this.f.setVisibility(0);
    }

    public void setBackIcon(int i) {
        b(i, new OnClickListener(this) {
            final /* synthetic */ HollowNavigationBar a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Context context = this.a.getContext();
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        });
    }

    public void a(final RoomDataBean roomDataBean, @Nullable final MemberDataBean memberDataBean) {
        if (this.e == null) {
            return;
        }
        if (roomDataBean == null) {
            this.e.setVisibility(4);
            return;
        }
        this.e.setVisibility(0);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HollowNavigationBar c;

            public void onClick(View view) {
                b.c("x_room:" + roomDataBean + "  x_member:" + memberDataBean);
                Activity a = cn.xiaochuankeji.tieba.background.utils.b.a(view.getContext());
                if (a != null) {
                    cn.xiaochuankeji.tieba.ui.chat.a.b.a(a, roomDataBean, memberDataBean, roomDataBean.member);
                }
            }
        });
    }
}
