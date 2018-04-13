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
import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.g;
import cn.xiaochuankeji.tieba.ui.utils.e;
import org.greenrobot.eventbus.c;
import rx.b.b;

public class HollowFeedHugView extends LinearLayout {
    private AnimationDrawable a;
    private HollowRecommendItemBean b;
    private ImageView c;
    private TextView d;

    public HollowFeedHugView(Context context) {
        super(context);
        a();
    }

    public HollowFeedHugView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HollowFeedHugView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_hug_view_feed, this);
        this.c = (ImageView) findViewById(R.id.hollow_feed_hug_status);
        this.d = (TextView) findViewById(R.id.hollow_feed_hug_count);
        this.a = (AnimationDrawable) getContext().getResources().getDrawable(R.drawable.anim_hollow_support_feed);
    }

    public void setRoomData(HollowRecommendItemBean hollowRecommendItemBean) {
        this.b = hollowRecommendItemBean;
        a(false);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HollowFeedHugView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (a.a((cn.xiaochuankeji.tieba.ui.base.a) this.a.getContext(), "flow_feed", 44)) {
                    this.a.b();
                }
            }
        });
    }

    private void b() {
        if (this.b.hugged == 1) {
            d();
        } else {
            c();
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void a(boolean z) {
        this.d.setText("" + e.a(this.b.hugs));
        if (z) {
            this.c.setImageDrawable(this.a);
            this.a.start();
            return;
        }
        this.c.setImageResource(this.b.hugged == 1 ? R.drawable.hug_6_feed : R.drawable.hug_1_feed);
    }

    private void c() {
        this.b.hugged = 1;
        HollowRecommendItemBean hollowRecommendItemBean = this.b;
        hollowRecommendItemBean.hugs++;
        a(true);
        cn.xiaochuankeji.tieba.api.hollow.a.c(this.b.id).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new b<EmptyJson>(this) {
            final /* synthetic */ HollowFeedHugView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                e.e(this.a.getContext());
                c.a().d(new g(this.a.b));
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ HollowFeedHugView a;

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

    private void d() {
        if (this.a.isRunning()) {
            this.a.stop();
        }
        this.b.hugged = 0;
        HollowRecommendItemBean hollowRecommendItemBean = this.b;
        hollowRecommendItemBean.hugs--;
        a(false);
        cn.xiaochuankeji.tieba.api.hollow.a.d(this.b.id).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new b<EmptyJson>(this) {
            final /* synthetic */ HollowFeedHugView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                c.a().d(new g(this.a.b));
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ HollowFeedHugView a;

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
