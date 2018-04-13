package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.utils.ResourceManager;

public class SelectCountryTitleView extends RelativeLayout {
    private TextView mTitleTv;

    public SelectCountryTitleView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(-1, ResourceManager.dp2px(getContext(), 24)));
        setBackgroundDrawable(ResourceManager.getDrawable(getContext(), "tableview_sectionheader_background.png"));
        this.mTitleTv = new TextView(getContext());
        this.mTitleTv.setTextSize(14.0f);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.leftMargin = ResourceManager.dp2px(getContext(), 10);
        this.mTitleTv.setLayoutParams(layoutParams);
        this.mTitleTv.setGravity(3);
        this.mTitleTv.setTextColor(-7171438);
        this.mTitleTv.setGravity(16);
        addView(this.mTitleTv);
    }

    public void setTitle(String str) {
        this.mTitleTv.setText(str);
    }

    public void update(String str) {
        setTitle(str);
    }
}
