package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public class SettingItem extends RelativeLayout {
    public SettingItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.setting_item, this, true);
        a(context, attributeSet);
    }

    private TextView getSubTitleView() {
        return (TextView) findViewById(R.id.sub_title);
    }

    private TextView getTitleView() {
        return (TextView) findViewById(R.id.title);
    }

    private void setTitle(String str) {
        getTitleView().setText(str);
    }

    public void setSubTitle(String str) {
        getSubTitleView().setText(str);
    }

    private CheckBox getCheckBoxView() {
        return (CheckBox) findViewById(R.id.checkbox);
    }

    private ImageView getDivide() {
        return (ImageView) findViewById(R.id.divide_line);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        getCheckBoxView().setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void setDivideVisibility(int i) {
        getDivide().setVisibility(i);
    }

    public void setChecked(boolean z) {
        getCheckBoxView().setChecked(z);
    }

    public void showTip(boolean z) {
        getTip().setVisibility(z ? 0 : 8);
    }

    private View getTip() {
        return findViewById(R.id.tip);
    }

    private void a(Context context, AttributeSet attributeSet) {
        LogUtil.d("top-bar-widget-initUI");
        if (UIHelper.isNightTheme()) {
            setBackgroundResource(R.drawable.edit_single_info_bg_dark);
        } else {
            setBackgroundResource(R.drawable.edit_single_info_bg);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SettingItem, 0, 0);
        String string = obtainStyledAttributes.getString(1);
        CharSequence string2 = obtainStyledAttributes.getString(0);
        boolean z = obtainStyledAttributes.getBoolean(2, false);
        boolean z2 = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
        setTitle(string);
        if (TextUtils.isEmpty(string2)) {
            getSubTitleView().setVisibility(8);
        } else {
            getSubTitleView().setVisibility(0);
            getSubTitleView().setText(string2);
        }
        if (z) {
            getCheckBoxView().setVisibility(0);
        } else {
            getCheckBoxView().setVisibility(8);
        }
        if (z2) {
            getDivide().setVisibility(0);
        } else {
            getDivide().setVisibility(8);
        }
    }
}
