package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.content.res.Resources;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.BigImageView;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.h;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class TaleImageHolder extends b {
    @BindView
    BigImageView image;
    @BindView
    TextView tv_loading;

    public TaleImageHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        a aVar = taleComment.tale;
        Resources resources = this.itemView.getResources();
        RectF a = b.a(resources, (float) aVar.d, (float) aVar.e);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        int a2 = (int) i.a(resources, a.width());
        int a3 = (int) i.a(resources, a.height());
        int a4 = (int) (((float) a2) - (i.a(resources, 13.0f) * 2.0f));
        a3 = (int) ((((float) a3) * ((float) a4)) / ((float) a2));
        com.izuiyou.a.a.b.c("tale.w:" + aVar.d + "  tale.h:" + aVar.e + "  w:" + a4 + "  h:" + a3);
        layoutParams.width = a4;
        layoutParams.height = a3;
        layoutParams.gravity = 1;
        this.tv_loading.setLayoutParams(layoutParams);
        this.tv_loading.setTag(aVar);
        this.tv_loading.setOnClickListener(this.b.c);
        SubsamplingScaleImageView ssiv = this.image.getSSIV();
        if (ssiv != null) {
            ssiv.setTag(aVar);
            ssiv.setQuickScaleEnabled(false);
            ssiv.setPanEnabled(false);
            ssiv.setZoomEnabled(false);
            ssiv.a();
            this.image.c();
        }
        this.image.setLayoutParams(layoutParams);
        this.image.setOnClickListener(this.b.c);
        this.image.setInitScaleType(1);
        this.image.setRecycleWhenDetached(false);
        Uri parse = Uri.parse(b.e(aVar.c));
        this.image.setProgressIndicator(new h(this) {
            final /* synthetic */ TaleImageHolder a;

            {
                this.a = r1;
            }

            public View a(BigImageView bigImageView) {
                return null;
            }

            public void a() {
                this.a.tv_loading.setVisibility(0);
            }

            public void a(int i) {
            }

            public void b() {
                this.a.tv_loading.setVisibility(8);
            }
        });
        this.image.a(parse);
    }
}
