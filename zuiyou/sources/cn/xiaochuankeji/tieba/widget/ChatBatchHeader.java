package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import com.izuiyou.a.a.b;
import com.scwang.smartrefresh.layout.a.e;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class ChatBatchHeader extends FrameLayout implements e {
    private OnClickListener a;

    public ChatBatchHeader(Context context) {
        super(context);
        b();
    }

    public ChatBatchHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public ChatBatchHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        View appCompatTextView = new AppCompatTextView(getContext());
        appCompatTextView.setText("批量删除");
        appCompatTextView.setTextColor(a.a().a(R.color.CM));
        appCompatTextView.setTextSize(1, 12.0f);
        appCompatTextView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChatBatchHeader a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.a != null) {
                    this.a.a.onClick(view);
                }
            }
        });
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        addView(appCompatTextView, layoutParams);
        setVisibility(4);
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
    }

    @NonNull
    public View getView() {
        return this;
    }

    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    public void setPrimaryColors(int... iArr) {
    }

    public void a(g gVar, int i, int i2) {
    }

    public void a(float f, int i, int i2) {
    }

    public void b(h hVar, int i, int i2) {
    }

    public int a(h hVar, boolean z) {
        return 0;
    }

    public boolean a() {
        return false;
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
        b.e(refreshState2.name());
        switch (refreshState2) {
            case LoadFinish:
                setVisibility(4);
                return;
            case Refreshing:
                setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void setOnHeaderListener(OnClickListener onClickListener) {
        this.a = onClickListener;
    }
}
