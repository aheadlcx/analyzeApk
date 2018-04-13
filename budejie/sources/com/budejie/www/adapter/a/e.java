package com.budejie.www.adapter.a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.htmlpage.NoViewActivity;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.type.MySquareIcon;
import com.budejie.www.util.an;
import com.budejie.www.util.c;
import com.umeng.analytics.MobclickAgent;
import java.util.List;

public class e extends BaseAdapter {
    public BadgeView a;
    private List<MySquareIcon> b;
    private Context c;
    private SharedPreferences d;

    public e(List<MySquareIcon> list, Context context) {
        this.b = list;
        this.c = context;
        this.d = context.getSharedPreferences("weiboprefer", 0);
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
        View inflate = View.inflate(this.c, R.layout.my_square_icon_item_layout, null);
        final MySquareIcon mySquareIcon = (MySquareIcon) this.b.get(i);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.my_square_icon_layout);
        AsyncImageView asyncImageView = (AsyncImageView) inflate.findViewById(R.id.my_square_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.my_square_icon_name);
        Log.i("MySquareIconAdapter", "mySquareIcon.getDefaultIcon()=" + mySquareIcon.getDefaultIcon());
        if (TextUtils.isEmpty(mySquareIcon.getIcon())) {
            asyncImageView.setImageResource(mySquareIcon.getDefaultIcon());
        } else {
            asyncImageView.setAsyncCacheImage(mySquareIcon.getIcon());
        }
        c.a(asyncImageView);
        textView.setText(mySquareIcon.getName());
        if ("0".equals(Integer.valueOf(mySquareIcon.getId())) || "更多".equals(mySquareIcon.getName())) {
            mySquareIcon.setUrl("mod://App_To_MoreIcon");
        }
        linearLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e b;

            public void onClick(View view) {
                MobclickAgent.onEvent(this.b.c, "E02-A06", mySquareIcon.getName() + "");
                Log.i("MySquareIconAdapter", mySquareIcon.getName());
                this.b.c.startActivity(new Intent(this.b.c, NoViewActivity.class).setData(Uri.parse(mySquareIcon.getUrl())).addFlags(268435456));
            }
        });
        if ("审帖".equals(mySquareIcon.getName()) || "mod://BDJ_To_Check".equals(mySquareIcon.getUrl())) {
            boolean z = this.d.getBoolean("shenheUpdate", false);
            this.a = an.a(this.c, linearLayout, false, 8, 8, 2, 0.0f);
            if (z) {
                this.a.b();
            } else {
                this.a.b();
            }
        }
        return inflate;
    }
}
