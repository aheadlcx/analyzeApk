package com.budejie.www.adapter.a;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.RoundAsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.type.SearchItem;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;

public class o extends BaseAdapter implements OnClickListener {
    protected Toast a;
    private Activity b;
    private List<SearchItem> c = new ArrayList();
    private g d;

    public o(Activity activity) {
        this.b = activity;
        this.d = new g(activity);
    }

    public void a(List<SearchItem> list) {
        if (this.c != null) {
            this.c.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void b(List<SearchItem> list) {
        this.c.clear();
        if (this.c != null) {
            this.c.addAll(list);
        }
        notifyDataSetInvalidated();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        o$b o_b;
        if (view == null) {
            view = this.b.getLayoutInflater().inflate(R.layout.search_item_layout, null);
            o_b = new o$b(this);
            o_b.a = (RoundAsyncImageView) view.findViewById(R.id.writer_profile);
            o_b.b = (TextView) view.findViewById(R.id.nice_name);
            o_b.c = (TextView) view.findViewById(R.id.fans_count);
            o_b.d = (TextView) view.findViewById(R.id.posts_count);
            o_b.e = (TextView) view.findViewById(R.id.comment_count);
            o_b.g = (TextView) view.findViewById(R.id.add_btn);
            o_b.f = (TextView) view.findViewById(R.id.cancel_btn);
            view.setTag(o_b);
        } else {
            o_b = (o$b) view.getTag();
        }
        SearchItem searchItem = (SearchItem) this.c.get(i);
        a(searchItem, o_b);
        b(searchItem, o_b);
        view.setOnClickListener(new o$a(this, searchItem.getId(), this.b, null));
        return view;
    }

    private void a(SearchItem searchItem, o$b o_b) {
        if (searchItem != null) {
            o_b.f.setOnClickListener(this);
            o_b.f.setTag(searchItem);
            o_b.g.setOnClickListener(this);
            o_b.g.setTag(searchItem);
            String relationship = searchItem.getRelationship();
            if ("1".equals(relationship)) {
                o_b.f.setVisibility(0);
                o_b.g.setVisibility(8);
            } else if ("0".equals(relationship)) {
                o_b.f.setVisibility(8);
                o_b.g.setVisibility(0);
            } else {
                o_b.f.setVisibility(8);
                o_b.g.setVisibility(8);
            }
        }
    }

    private void b(SearchItem searchItem, o$b o_b) {
        o_b.a.setAsyncCacheImage(searchItem.getProfileImage(), R.color.head_portrait_male_round);
        CharSequence username = searchItem.getUsername();
        if (TextUtils.isEmpty(username)) {
            username = "";
        }
        o_b.b.setText(username);
        a(o_b.c, R.string.fans_count, searchItem.getFansCount());
        a(o_b.d, R.string.posts_count, searchItem.getPostsCount());
        a(o_b.e, R.string.comment_count, searchItem.getCommentsCount());
    }

    private void a(TextView textView, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        textView.setText(this.b.getString(i, new Object[]{str}));
    }

    public boolean isEmpty() {
        return this.c == null || this.c.isEmpty();
    }

    public void onClick(View view) {
        if (!an.a(this.b)) {
            an.a(this.b, this.b.getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(this.b))) {
            an.a(this.b, 0, null, null, 0);
        } else {
            SearchItem searchItem = (SearchItem) view.getTag();
            String relationship = searchItem.getRelationship();
            if (relationship.equals("1")) {
                a(searchItem);
            } else if (relationship.equals("0")) {
                b(searchItem);
            }
        }
    }

    private void a(SearchItem searchItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e(this.b, searchItem.getId()), new o$1(this, searchItem));
    }

    private void b(SearchItem searchItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().d(this.b, searchItem.getId()), new o$2(this, searchItem));
    }
}
