package com.budejie.www.activity.label.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.label.view.SwipeLayout;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchItem$TypeEnum;
import java.util.List;

public class c extends BaseAdapter {
    private List<SearchItem> a;
    private Context b;
    private LayoutInflater c = LayoutInflater.from(this.b);
    private boolean d;
    private a e;

    public interface a {
        void a();

        void a(SearchItem searchItem);

        void b(SearchItem searchItem);
    }

    public c(Context context, List<SearchItem> list, boolean z) {
        this.b = context;
        this.a = list;
        this.d = z;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public int getCount() {
        return com.budejie.www.goddubbing.c.a.a(this.a) ? 0 : this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        c$b c_b;
        if (view == null) {
            c$b c_b2 = new c$b(this, null);
            view = this.c.inflate(R.layout.label_add_moderator_item, null);
            c_b2.a = (AsyncImageView) view.findViewById(R.id.portrait_image_view);
            c_b2.b = (TextView) view.findViewById(R.id.user_name_text_view);
            c_b2.c = (RelativeLayout) view.findViewById(R.id.item_layout);
            c_b2.d = (SwipeLayout) view.findViewById(R.id.swipe_layout);
            c_b2.e = (TextView) view.findViewById(R.id.delete_deputy_button);
            view.setTag(c_b2);
            c_b = c_b2;
        } else {
            c_b = (c$b) view.getTag();
        }
        if (!this.d) {
            c_b.d.setNeedDrag(false);
        }
        a(c_b, i);
        return view;
    }

    private void a(c$b c_b, int i) {
        if (c_b != null) {
            SearchItem searchItem = (SearchItem) this.a.get(i);
            if (searchItem != null) {
                c_b.d.setOnClickListener(new c$1(this, searchItem));
                c_b.e.setOnClickListener(new c$2(this, searchItem));
                if (this.d && searchItem.getType() == SearchItem$TypeEnum.ADD_MODERATOR.getValue()) {
                    c_b.a.setImageResource(com.budejie.www.h.c.a().b(R.attr.label_apply_moderator_theme));
                    c_b.b.setText("添加副版主");
                    c_b.d.setNeedDrag(false);
                    return;
                }
                String profileImageLarge = searchItem.getProfileImageLarge();
                if (TextUtils.isEmpty(profileImageLarge)) {
                    profileImageLarge = searchItem.getProfileImage();
                }
                c_b.a.setPostAvatarImage(profileImageLarge);
                c_b.b.setText(searchItem.getUsername());
            }
        }
    }
}
