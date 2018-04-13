package com.tencent.weibo.sdk.android.api.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import java.util.List;

public class ConversationAdapter extends BaseAdapter {
    private Context ct;
    private List<String> cvlist;

    public ConversationAdapter(Context context, List<String> list) {
        this.ct = context;
        this.cvlist = list;
    }

    public int getCount() {
        return this.cvlist.size();
    }

    public Object getItem(int i) {
        return this.cvlist.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public List<String> getCvlist() {
        return this.cvlist;
    }

    public void setCvlist(List<String> list) {
        this.cvlist = list;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new LinearLayout(this.ct);
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            view.setBackgroundDrawable(BackGroudSeletor.getdrawble("topic_", this.ct));
            view.setGravity(16);
            view.setPadding(10, 0, 10, 0);
            View textView = new TextView(this.ct);
            textView.setTextColor(Color.parseColor("#108ece"));
            textView.setText(getItem(i).toString());
            textView.setGravity(16);
            textView.setTextSize(18.0f);
            view.addView(textView);
            view.setTag(textView);
            return view;
        }
        ((TextView) view.getTag()).setText(getItem(i).toString());
        return view;
    }
}
