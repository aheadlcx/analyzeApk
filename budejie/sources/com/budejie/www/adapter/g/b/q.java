package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.DetailContentActivity;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.h.c;
import com.budejie.www.util.an;

public class q extends a<ListItemObject> {
    public TextView e;
    public TextView f;
    public Button g;
    public TextView h;
    public ImageButton i;

    public q(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.new_new_list_item_post_state, viewGroup);
        this.e = (TextView) inflate.findViewById(R.id.tougao_state);
        this.f = (TextView) inflate.findViewById(R.id.tougaoing);
        this.g = (Button) inflate.findViewById(R.id.resetSend);
        this.h = (TextView) inflate.findViewById(R.id.tougao_time);
        this.i = (ImageButton) inflate.findViewById(R.id.imgbtn_more);
        if (this.b.e == 11) {
            this.i.setImageResource(c.a().b(R.attr.close_btn));
        }
        return inflate;
    }

    public void c() {
        if (!((ListItemObject) this.c).getIsDraftBean()) {
            CharSequence status_text = ((ListItemObject) this.c).getStatus_text();
            if (!TextUtils.isEmpty(status_text) && status_text.length() > 10) {
                status_text = status_text.substring(0, 10) + "\n" + status_text.substring(10);
            }
            this.e.setText(status_text);
            Object addtime = ((ListItemObject) this.c).getAddtime();
            if (!TextUtils.isEmpty(addtime)) {
                this.h.setText(addtime.substring(0, 10));
            }
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            this.e.setVisibility(0);
            this.i.setVisibility(0);
        } else if (((ListItemObject) this.c).getState() == 0) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            this.f.setText("发送中......");
            this.g.setVisibility(8);
            this.i.setVisibility(8);
        } else if (((ListItemObject) this.c).getState() == -1 || !an.a(this.a)) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            this.f.setText("发送失败");
            this.g.setVisibility(0);
            this.g.setOnClickListener(this);
            this.i.setVisibility(0);
        } else if (((ListItemObject) this.c).getState() == 1) {
            this.e.setVisibility(0);
            this.e.setText(R.string.sendsuccess);
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            this.i.setVisibility(0);
        }
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tougao_state:
            case R.id.tougaoing:
                Intent intent = new Intent(this.a, DetailContentActivity.class);
                intent.putExtra("operator", "help");
                intent.putExtra("url", "http://www.budejie.com/budejie/help.html");
                this.a.startActivity(intent);
                return;
            case R.id.resetSend:
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                this.f.setText("发送中......");
                this.g.setVisibility(8);
                this.i.setVisibility(8);
                if (this.b.c instanceof com.budejie.www.adapter.e.b) {
                    ((com.budejie.www.adapter.e.b) this.b.c).a((ListItemObject) this.c);
                    return;
                }
                return;
            case R.id.imgbtn_more:
                view.setTag(Integer.valueOf(this.d));
                this.b.c.c(view, (ListItemObject) this.c);
                return;
            default:
                return;
        }
    }
}
