package qsbk.app.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.adapter.EditUserInfoAdapter$UserInfoItem;
import qsbk.app.api.StartActivityListenerForClick;
import qsbk.app.utils.UIHelper;

public class EditUserInfoItem {
    private final EditUserInfoAdapter$UserInfoItem a;
    private final RelativeLayout b;
    private final Context c;
    private final TextView d;
    private final TextView e;
    private OnClickListener f;

    public EditUserInfoItem(EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem, Context context) {
        this(editUserInfoAdapter$UserInfoItem, context, null, null);
    }

    public EditUserInfoItem(EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem, Context context, StartActivityListenerForClick startActivityListenerForClick) {
        this(editUserInfoAdapter$UserInfoItem, context, startActivityListenerForClick, null);
    }

    public EditUserInfoItem(EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem, Context context, ViewGroup viewGroup) {
        this(editUserInfoAdapter$UserInfoItem, context, null, viewGroup);
    }

    public EditUserInfoItem(EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem, Context context, StartActivityListenerForClick startActivityListenerForClick, ViewGroup viewGroup) {
        this.a = editUserInfoAdapter$UserInfoItem;
        this.c = context;
        this.f = startActivityListenerForClick;
        if (viewGroup != null) {
            this.b = (RelativeLayout) LayoutInflater.from(this.c).inflate(R.layout.layout_user_info_item, viewGroup, false);
        } else {
            this.b = (RelativeLayout) LayoutInflater.from(this.c).inflate(R.layout.layout_user_info_item, null);
        }
        this.d = (TextView) this.b.findViewById(R.id.description);
        this.e = (TextView) this.b.findViewById(R.id.value);
        a();
    }

    private void a() {
        if (this.f != null) {
            setOnEditListener(this.f);
        }
        a(this.e, this.a);
        b(this.d, this.a);
    }

    public View getView() {
        return this.b;
    }

    public TextView getValueView() {
        return this.e;
    }

    private void a(TextView textView, EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem) {
        CharSequence displayValue = editUserInfoAdapter$UserInfoItem.getDisplayValue();
        if (TextUtils.isEmpty(displayValue)) {
            displayValue = editUserInfoAdapter$UserInfoItem.getDefaultDisplayValue();
        }
        textView.setTextColor(this.c.getResources().getColor(UIHelper.isNightTheme() ? R.color.one_profile_item_content_dark : R.color.g_txt_middle));
        if (displayValue == null) {
            displayValue = "";
        }
        textView.setText(displayValue);
    }

    private void b(TextView textView, EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem) {
        textView.setText(editUserInfoAdapter$UserInfoItem.getDisplayDesription());
    }

    public EditUserInfoAdapter$UserInfoItem getUserInfo() {
        return this.a;
    }

    public void setOnEditListener(OnClickListener onClickListener) {
        this.b.setTag(this.a);
        this.b.setOnClickListener(onClickListener);
    }
}
