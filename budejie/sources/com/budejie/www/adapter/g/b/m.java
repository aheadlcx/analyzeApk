package com.budejie.www.adapter.g.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.constants.CommonInts;
import com.budejie.www.R;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.d;
import com.umeng.analytics.MobclickAgent;

public class m extends a<ListItemObject> {
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private long i;
    private View j;
    private RelativeLayout k;
    private RelativeLayout l;
    private RelativeLayout m;
    private RelativeLayout n;

    public m(Context context, b bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.new_new_list_item_comment_new, viewGroup);
        this.e = (TextView) inflate.findViewById(R.id.dingCount);
        this.f = (TextView) inflate.findViewById(R.id.caiCount);
        this.g = (TextView) inflate.findViewById(R.id.shareCount);
        this.h = (TextView) inflate.findViewById(R.id.commentCount);
        this.j = inflate.findViewById(R.id.operatrion_layout);
        this.k = (RelativeLayout) inflate.findViewById(R.id.ding_layout);
        this.l = (RelativeLayout) inflate.findViewById(R.id.cai_layout);
        this.m = (RelativeLayout) inflate.findViewById(R.id.share_layout);
        this.n = (RelativeLayout) inflate.findViewById(R.id.comment_layout);
        this.b.f.a = this.k;
        this.b.f.b = this.m;
        return inflate;
    }

    public void c() {
        if (((ListItemObject) this.c).getIsDraftBean()) {
            this.j.setVisibility(8);
            return;
        }
        this.j.setVisibility(0);
        if (((ListItemObject) this.c).getStatus() == 1 && ((ListItemObject) this.c).getIsIsRecsysData()) {
            this.e.setText("赞");
            this.f.setText("踩");
        } else {
            this.e.setText(Integer.toString(((ListItemObject) this.c).getLove()));
            this.f.setText(Integer.toString(((ListItemObject) this.c).getCai()));
        }
        this.k.setSelected("ding".equals(((ListItemObject) this.c).getFlag()));
        this.l.setSelected("cai".equals(((ListItemObject) this.c).getCai_flag()));
        if ("null".equals(((ListItemObject) this.c).getRepost())) {
            this.g.setText("0");
        } else {
            int parseInt;
            try {
                parseInt = Integer.parseInt(((ListItemObject) this.c).getRepost());
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            if (parseInt > CommonInts.USER_MANAGER_REQUEST_CODE) {
                this.g.setText("9999+");
            } else if (parseInt != 0) {
                this.g.setText(String.valueOf(parseInt));
            } else {
                this.g.setText(R.string.forward_share);
            }
        }
        CharSequence comment = ((ListItemObject) this.c).getComment();
        if ("0".equals(comment)) {
            this.h.setText(R.string.commend);
        } else {
            this.h.setText(comment);
        }
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
    }

    public void onClick(View view) {
        int i = 0;
        TextView textView;
        int parseInt;
        switch (view.getId()) {
            case R.id.ding_layout:
                if (!an.a(this.a)) {
                    an.a((Activity) this.a, this.a.getString(R.string.nonet), -1).show();
                } else if (!view.isSelected() && !"cai".equals(((ListItemObject) this.c).getCai_flag())) {
                    i.a(this.a.getString(R.string.track_event_top_post), j.a((ListItemObject) this.c), j.b(this.a, (ListItemObject) this.c));
                    d.a(this.a, view, "1");
                    textView = (TextView) view.findViewById(R.id.dingCount);
                    try {
                        parseInt = Integer.parseInt(textView.getText().toString());
                    } catch (NumberFormatException e) {
                        parseInt = i;
                    }
                    if (((ListItemObject) this.c).getStatus() == 1 && ((ListItemObject) this.c).getIsIsRecsysData()) {
                        textView.setText("赞");
                    } else {
                        textView.setText(String.valueOf(parseInt + 1));
                    }
                    view.setSelected(true);
                    ((ListItemObject) this.c).setLove(((ListItemObject) this.c).getLove() + 1);
                    ((ListItemObject) this.c).setFlag("ding");
                } else {
                    return;
                }
                this.b.c.a(view, (ListItemObject) this.c);
                return;
            case R.id.cai_layout:
                if (!an.a(this.a)) {
                    an.a((Activity) this.a, this.a.getString(R.string.nonet), -1).show();
                } else if (!view.isSelected() && !"ding".equals(((ListItemObject) this.c).getFlag())) {
                    i.a(this.a.getString(R.string.track_event_down_post), j.a((ListItemObject) this.c), j.b(this.a, (ListItemObject) this.c));
                    d.a(this.a, view, "1");
                    textView = (TextView) view.findViewById(R.id.caiCount);
                    try {
                        parseInt = Integer.parseInt(textView.getText().toString());
                    } catch (NumberFormatException e2) {
                        parseInt = i;
                    }
                    if (((ListItemObject) this.c).getStatus() == 1 && ((ListItemObject) this.c).getIsIsRecsysData()) {
                        textView.setText("踩");
                    } else {
                        textView.setText(String.valueOf(parseInt + 1));
                    }
                    view.setSelected(true);
                    ((ListItemObject) this.c).setCai(((ListItemObject) this.c).getCai() + 1);
                    ((ListItemObject) this.c).setCai_flag("cai");
                } else {
                    return;
                }
                this.b.c.b(view, (ListItemObject) this.c);
                return;
            case R.id.share_layout:
                if (System.currentTimeMillis() - this.i > 300) {
                    this.i = System.currentTimeMillis();
                    this.b.c.a(view, (ListItemObject) this.c, this.d);
                    k.a(this.a).k();
                    return;
                }
                return;
            case R.id.comment_layout:
                MobclickAgent.onEvent(this.a, "帖子流热门评论", "点击次数");
                this.b.c.d(view, (ListItemObject) this.c);
                return;
            default:
                return;
        }
    }
}
