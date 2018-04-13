package cn.xiaochuankeji.tieba.ui.widget.image;

import android.content.Context;
import android.util.AttributeSet;
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.generic.b;

public class TopRoundImageView extends WebImageView {
    public TopRoundImageView(Context context) {
        super(context);
        a();
    }

    public TopRoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TopRoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void a() {
        b bVar = new b(getResources());
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.a((float) e.a(5.0f), (float) e.a(5.0f), 0.0f, 0.0f);
        bVar.a(roundingParams);
        setHierarchy(bVar.t());
    }
}
