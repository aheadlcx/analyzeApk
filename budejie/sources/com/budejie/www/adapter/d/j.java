package com.budejie.www.adapter.d;

import android.app.Activity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.f.f;
import com.budejie.www.bean.ListItemObject;
import com.umeng.onlineconfig.OnlineConfigAgent;

public class j extends a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;

    public /* synthetic */ Object d() {
        return a();
    }

    public j(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
    }

    public View b() {
        f fVar = new f();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.new_new_list_item_share, null);
        fVar.a = (TextView) viewGroup.findViewById(R.id.sharetofriend);
        fVar.b = (TextView) viewGroup.findViewById(R.id.share);
        viewGroup.setTag(fVar);
        return viewGroup;
    }

    public int c() {
        return RowType.SHARE_ROW.ordinal();
    }

    public void a(b bVar) {
        f fVar = (f) bVar;
        ListItemObject listItemObject = new ListItemObject();
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this.b, "推荐朋友-SMS-内容");
        if (TextUtils.isEmpty(configParams)) {
            configParams = "发现一个好玩的应用，百思不得姐，瞬间戳中你的笑点，试试看吧。http://www.budejie.com/budejie/";
        }
        listItemObject.setContent(configParams);
        fVar.a.setTag(listItemObject);
        fVar.a.setOnClickListener(this.d.b());
        CharSequence spannableString = new SpannableString("果断分享");
        spannableString.setSpan(new AbsoluteSizeSpan(100), 2, 3, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(25), 3, 4, 33);
        fVar.b.setText(spannableString);
    }

    public ListItemObject a() {
        return this.a;
    }
}
