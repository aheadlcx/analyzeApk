package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.budejie.www.R;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import java.io.Serializable;

public class h extends a<ListItemObject> {
    private ImageButton e;

    public h(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_history_hot_post_head, viewGroup);
        this.e = (ImageButton) inflate.findViewById(R.id.history_post_see_more);
        return inflate;
    }

    public void c() {
        this.e.setOnClickListener(this);
    }

    public void onClick(View view) {
        this.a.getSharedPreferences("weiboprefer", 0);
        Intent intent = new Intent(this.a, PostsActivity.class);
        intent.putExtra("tag_all", "tag_suiji");
        intent.putExtra("navigation_key", (Serializable) HomeGroup.z.menus.get(2));
        this.a.startActivity(intent);
        i.a(this.a.getString(R.string.track_event_see_history_hot_post), j.a((ListItemObject) this.c), j.b(this.a, (ListItemObject) this.c));
    }
}
