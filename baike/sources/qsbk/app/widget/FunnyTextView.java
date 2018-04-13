package qsbk.app.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.UIHelper;

public class FunnyTextView extends LinearLayout {
    public TextView leftBaseTextView;
    public TextView rightBaseTextView;
    public TickerView tickerView;

    public FunnyTextView(Context context) {
        this(context, null);
    }

    public FunnyTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunnyTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        if (this.tickerView == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.funny_text_layout, this);
            this.leftBaseTextView = (TextView) inflate.findViewById(R.id.ticker_left);
            this.tickerView = (TickerView) inflate.findViewById(R.id.ticker_content);
            this.tickerView.setCharacterList(UIHelper.getDefaultNumberList(true));
            this.tickerView.setTypeface(Typeface.SANS_SERIF);
            this.rightBaseTextView = (TextView) inflate.findViewById(R.id.ticker_right);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (this.tickerView != null) {
            this.tickerView.setTypeface(typeface);
        }
    }

    public void requestLeftTickerView() {
        this.leftBaseTextView.setVisibility(0);
        this.rightBaseTextView.setVisibility(8);
    }

    public void requestRightTickerView() {
        this.leftBaseTextView.setVisibility(8);
        this.rightBaseTextView.setVisibility(0);
    }

    public void setTextSize(float f) {
        if (this.leftBaseTextView.getVisibility() == 0) {
            this.leftBaseTextView.setTextSize(f);
        }
        this.tickerView.setTextSize(TypedValue.applyDimension(2, f, getResources().getDisplayMetrics()));
        if (this.rightBaseTextView.getVisibility() == 0) {
            this.rightBaseTextView.setTextSize(f);
        }
    }

    public void setTextColor(int i) {
        if (this.leftBaseTextView.getVisibility() == 0) {
            this.leftBaseTextView.setTextColor(i);
        }
        this.tickerView.setTextColor(i);
        if (this.rightBaseTextView.getVisibility() == 0) {
            this.rightBaseTextView.setTextColor(i);
        }
    }

    public void setBaseText(String str) {
        if (this.leftBaseTextView.getVisibility() == 0) {
            this.leftBaseTextView.setText(str);
        } else if (this.rightBaseTextView.getVisibility() == 0) {
            this.rightBaseTextView.setText(str);
        }
    }

    public void setText(String str, boolean z) {
        if (this.tickerView != null) {
            this.tickerView.setAnimationDuration((long) getContext().getResources().getInteger(R.integer.vote_duration));
            this.tickerView.setText(str, z);
        }
    }
}
