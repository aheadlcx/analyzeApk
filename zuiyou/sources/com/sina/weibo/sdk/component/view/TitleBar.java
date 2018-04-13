package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.utils.ResourceManager;

public class TitleBar extends RelativeLayout {
    public static final int TITLE_BAR_HEIGHT = 45;
    private ListenerOnTitleBtnClicked mClickListener;
    private TextView mLeftBtn;
    private TextView mTitleText;

    public interface ListenerOnTitleBtnClicked {
        void onLeftBtnClicked();
    }

    public TitleBar(Context context) {
        super(context);
        initViews();
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    private void initViews() {
        this.mLeftBtn = new TextView(getContext());
        this.mLeftBtn.setClickable(true);
        this.mLeftBtn.setTextSize(2, 17.0f);
        this.mLeftBtn.setTextColor(ResourceManager.createColorStateList(-32256, 1728020992));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(5);
        layoutParams.addRule(15);
        layoutParams.leftMargin = ResourceManager.dp2px(getContext(), 10);
        layoutParams.rightMargin = ResourceManager.dp2px(getContext(), 10);
        this.mLeftBtn.setLayoutParams(layoutParams);
        this.mLeftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (TitleBar.this.mClickListener != null) {
                    TitleBar.this.mClickListener.onLeftBtnClicked();
                }
            }
        });
        addView(this.mLeftBtn);
        this.mTitleText = new TextView(getContext());
        this.mTitleText.setTextSize(2, 18.0f);
        this.mTitleText.setTextColor(-11382190);
        this.mTitleText.setEllipsize(TruncateAt.END);
        this.mTitleText.setSingleLine(true);
        this.mTitleText.setGravity(17);
        this.mTitleText.setMaxWidth(ResourceManager.dp2px(getContext(), 160));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.mTitleText.setLayoutParams(layoutParams);
        addView(this.mTitleText);
        setLayoutParams(new LayoutParams(-1, ResourceManager.dp2px(getContext(), 45)));
        setBackgroundDrawable(ResourceManager.getNinePatchDrawable(getContext(), "weibosdk_navigationbar_background.9.png"));
    }

    public void setTitleBarText(String str) {
        this.mTitleText.setText(str);
    }

    public void setLeftBtnText(String str) {
        this.mLeftBtn.setText(str);
    }

    public void setLeftBtnBg(Drawable drawable) {
        this.mLeftBtn.setBackgroundDrawable(drawable);
    }

    public void setTitleBarClickListener(ListenerOnTitleBtnClicked listenerOnTitleBtnClicked) {
        this.mClickListener = listenerOnTitleBtnClicked;
    }
}
