package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class SupportOrNotView extends LinearLayout {
    private OnSupportListener a;
    public HighlightableImageButton support;
    public HighlightableImageButton unSupport;

    public interface OnSupportListener {
        void onSupportSelect(View view, boolean z);

        void onUnSupportSelect(View view, boolean z);
    }

    public SupportOrNotView(Context context) {
        this(context, null);
    }

    public SupportOrNotView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SupportOrNotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        if (this.support == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.support_or_not_layout, this);
            this.support = (HighlightableImageButton) inflate.findViewById(R.id.support);
            this.support.setHighlighted(false);
            this.support.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_support_night : R.drawable.operation_support));
            this.unSupport = (HighlightableImageButton) inflate.findViewById(R.id.unsupport);
            this.unSupport.setHighlighted(false);
            this.unSupport.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_unsupport_night : R.drawable.operation_unsupport));
        }
    }

    public void reset() {
        this.support.setEnabled(true);
        this.support.setClickable(true);
        this.support.setHighlighted(false);
        this.support.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_support_night : R.drawable.operation_support));
        this.unSupport.setEnabled(true);
        this.unSupport.setClickable(true);
        this.unSupport.setHighlighted(false);
        this.unSupport.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_unsupport_night : R.drawable.operation_unsupport));
    }

    public void setEnable(boolean z) {
        this.support.setEnabled(z);
        this.unSupport.setEnabled(z);
    }

    public void setSupport() {
        if (this.unSupport.isHighlighted()) {
            this.unSupport.setEnabled(true);
            this.unSupport.setHighlighted(false);
            this.unSupport.setClickable(true);
            this.unSupport.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_unsupport_night : R.drawable.operation_unsupport));
        }
        this.support.setEnabled(false);
        this.support.setHighlighted(true);
        this.support.setClickable(false);
        this.support.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_support_press));
    }

    public void setUnSupport() {
        if (this.support.isHighlighted()) {
            this.support.setEnabled(true);
            this.support.setHighlighted(false);
            this.support.setClickable(true);
            this.support.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_support_night : R.drawable.operation_support));
        }
        this.unSupport.setEnabled(false);
        this.unSupport.setHighlighted(true);
        this.unSupport.setClickable(false);
        this.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_unsupport_press));
    }

    public boolean isSupport() {
        return this.support.isHighlighted();
    }

    public boolean isUnsupport() {
        return this.unSupport.isHighlighted();
    }

    public void setOnSupportListener(OnSupportListener onSupportListener) {
        this.a = onSupportListener;
        if (this.support != null && this.a != null) {
            this.support.setOnClickListener(new es(this));
            this.unSupport.setOnClickListener(new et(this));
        }
    }
}
