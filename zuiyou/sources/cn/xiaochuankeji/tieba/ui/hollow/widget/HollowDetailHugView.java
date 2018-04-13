package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.ui.auth.a;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.detail.HollowDetailActivity;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.g;
import cn.xiaochuankeji.tieba.ui.utils.e;
import org.greenrobot.eventbus.c;
import rx.b.b;

public class HollowDetailHugView extends LinearLayout {
    private AnimationDrawable a;
    private RoomDataBean b;
    private ImageView c;
    private TextView d;

    public HollowDetailHugView(Context context) {
        super(context);
        a();
    }

    public HollowDetailHugView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HollowDetailHugView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_hug_view_detail, this);
        this.c = (ImageView) findViewById(R.id.hollow_head_hug_status);
        this.d = (TextView) findViewById(R.id.hollow_head_hug_count);
        this.a = (AnimationDrawable) getContext().getResources().getDrawable(R.drawable.anim_hollow_support_detail);
    }

    public void setRoomData(RoomDataBean roomDataBean) {
        this.b = roomDataBean;
        c();
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HollowDetailHugView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (a.a((HollowDetailActivity) this.a.getContext(), "hollow_detail", 44)) {
                    this.a.b();
                }
            }
        });
    }

    private void b() {
        if (this.b.hugged == 1) {
            e();
        } else {
            d();
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void c() {
        this.d.setTextColor(this.b.hugged == 1 ? -686198 : -6709587);
        this.d.setText("" + (this.b.hugs < 0 ? Integer.valueOf(0) : e.a(this.b.hugs)));
        this.c.setImageResource(this.b.hugged == 1 ? R.drawable.hug_6 : R.drawable.hug_1);
    }

    private void d() {
        this.b.hugged = 1;
        RoomDataBean roomDataBean = this.b;
        roomDataBean.hugs++;
        c();
        this.c.setImageDrawable(this.a);
        this.a.start();
        cn.xiaochuankeji.tieba.api.hollow.a.c(this.b.id).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new b<EmptyJson>(this) {
            final /* synthetic */ HollowDetailHugView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                e.e(this.a.getContext());
                c.a().d(new g(RoomDataBean.a(this.a.b)));
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ HollowDetailHugView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
            }
        });
    }

    private void e() {
        if (this.a.isRunning()) {
            this.a.stop();
        }
        this.b.hugged = 0;
        RoomDataBean roomDataBean = this.b;
        roomDataBean.hugs--;
        c();
        cn.xiaochuankeji.tieba.api.hollow.a.d(this.b.id).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new b<EmptyJson>(this) {
            final /* synthetic */ HollowDetailHugView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                c.a().d(new g(RoomDataBean.a(this.a.b)));
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ HollowDetailHugView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
            }
        });
    }
}
