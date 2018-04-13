package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;

public class PhoneTipView extends FrameLayout {
    private ImageView a;

    public PhoneTipView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PhoneTipView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        a();
    }

    public PhoneTipView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i);
        a();
    }

    public PhoneTipView(@NonNull Context context) {
        super(context);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_phone_tip, this);
        this.a = (ImageView) findViewById(R.id.tip_crumb);
    }

    public void setStatus(boolean z) {
        if (z) {
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(8);
        }
    }
}
