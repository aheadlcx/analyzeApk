package qsbk.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;

@Deprecated
public class EditUserInfoAdapter extends DefaultAdapter<EditUserInfoAdapter$UserInfoItem> {
    public EditUserInfoAdapter(ArrayList<EditUserInfoAdapter$UserInfoItem> arrayList, Activity activity) {
        super(arrayList, activity);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.n.inflate(R.layout.layout_user_info_item, null);
        }
        EditUserInfoAdapter$a a = EditUserInfoAdapter$a.a(view);
        EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem = (EditUserInfoAdapter$UserInfoItem) getItem(i);
        a(EditUserInfoAdapter$a.a(a), editUserInfoAdapter$UserInfoItem);
        b(EditUserInfoAdapter$a.b(a), editUserInfoAdapter$UserInfoItem);
        return view;
    }

    private void a(TextView textView, EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem) {
        CharSequence a = EditUserInfoAdapter$UserInfoItem.a(editUserInfoAdapter$UserInfoItem);
        if (TextUtils.isEmpty(a)) {
            a = EditUserInfoAdapter$UserInfoItem.b(editUserInfoAdapter$UserInfoItem);
            textView.setTextColor(this.k.getResources().getColor(R.color.g_txt_small));
        } else {
            textView.setTextColor(this.k.getResources().getColor(R.color.g_txt_middle));
        }
        if (a == null) {
            a = "";
        }
        textView.setText(a);
    }

    private void b(TextView textView, EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem) {
        textView.setText(EditUserInfoAdapter$UserInfoItem.c(editUserInfoAdapter$UserInfoItem));
    }
}
