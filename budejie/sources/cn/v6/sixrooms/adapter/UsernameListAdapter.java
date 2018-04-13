package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sixrooms.Manage;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.FindUserNameBean;
import cn.v6.sixrooms.ui.phone.FindUsernameActivity;
import cn.v6.sixrooms.ui.phone.RetrieveNameOrPasswordActivity;
import cn.v6.sixrooms.utils.GlobleValue;
import java.util.ArrayList;
import java.util.List;

public class UsernameListAdapter extends BaseAdapter {
    private Context a;
    private List<FindUserNameBean> b;

    public UsernameListAdapter(Context context, ArrayList<FindUserNameBean> arrayList) {
        this.a = context;
        this.b = arrayList;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        UsernameListAdapter$a usernameListAdapter$a;
        FindUserNameBean findUserNameBean = (FindUserNameBean) getItem(i);
        if (view == null) {
            UsernameListAdapter$a usernameListAdapter$a2 = new UsernameListAdapter$a((byte) 0);
            view = View.inflate(this.a, R.layout.phone_find_username_listview_item, null);
            UsernameListAdapter$a.a(usernameListAdapter$a2, (TextView) view.findViewById(R.id.tv_username_left));
            UsernameListAdapter$a.b(usernameListAdapter$a2, (TextView) view.findViewById(R.id.tv_username_right));
            UsernameListAdapter$a.a(usernameListAdapter$a2, view.findViewById(R.id.id_line));
            UsernameListAdapter$a.b(usernameListAdapter$a2, view.findViewById(R.id.id_bottom_line));
            view.setTag(usernameListAdapter$a2);
            usernameListAdapter$a = usernameListAdapter$a2;
        } else {
            usernameListAdapter$a = (UsernameListAdapter$a) view.getTag();
        }
        UsernameListAdapter$a.a(usernameListAdapter$a).setText(findUserNameBean.getUsernameLeft());
        if (i == this.b.size() - 1) {
            UsernameListAdapter$a.b(usernameListAdapter$a).setVisibility(4);
        } else {
            UsernameListAdapter$a.b(usernameListAdapter$a).setVisibility(0);
        }
        if (i == this.b.size() - 1 && TextUtils.isEmpty(findUserNameBean.getUsernameRight())) {
            UsernameListAdapter$a.c(usernameListAdapter$a).setVisibility(4);
            UsernameListAdapter$a.d(usernameListAdapter$a).setVisibility(4);
        } else {
            UsernameListAdapter$a.d(usernameListAdapter$a).setVisibility(0);
            UsernameListAdapter$a.c(usernameListAdapter$a).setVisibility(0);
            UsernameListAdapter$a.c(usernameListAdapter$a).setText(findUserNameBean.getUsernameRight());
        }
        UsernameListAdapter$a.a(usernameListAdapter$a).setOnClickListener(new j(this));
        UsernameListAdapter$a.c(usernameListAdapter$a).setOnClickListener(new k(this));
        return view;
    }

    static /* synthetic */ void a(UsernameListAdapter usernameListAdapter, String str) {
        GlobleValue.forgetUserName = str;
        Manage.getInstance().finishActivities(RetrieveNameOrPasswordActivity.class);
        ((FindUsernameActivity) usernameListAdapter.a).finishWithAnimation();
    }
}
