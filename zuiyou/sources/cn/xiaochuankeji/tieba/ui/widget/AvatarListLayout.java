package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.List;

public class AvatarListLayout extends LinearLayout {
    public AvatarListLayout(Context context) {
        super(context);
    }

    public AvatarListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AvatarListLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAvatarList(List<TaleAuthor> list) {
        setOrientation(0);
        removeAllViews();
        if (list != null) {
            if (VERSION.SDK_INT >= 17) {
                setLayoutDirection(1);
            }
            int i = 0;
            while (i < list.size()) {
                RingImageView ringImageView = (RingImageView) LayoutInflater.from(getContext()).inflate(R.layout.ring_image_view, null);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(e.a(20.0f), e.a(20.0f));
                if (i != 0) {
                    if (VERSION.SDK_INT >= 17) {
                        layoutParams.setMargins(0, 0, -e.a(8.0f), 0);
                    } else {
                        layoutParams.setMargins(-e.a(5.0f), 0, 0, 0);
                    }
                }
                ringImageView.setLayoutParams(layoutParams);
                if (list.get(i) != null) {
                    ringImageView.setWebImage(b.a(((TaleAuthor) list.get(i)).id, ((TaleAuthor) list.get(i)).avatar));
                }
                addView(ringImageView);
                if (i != 3) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
