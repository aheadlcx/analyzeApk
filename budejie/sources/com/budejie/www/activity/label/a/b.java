package com.budejie.www.activity.label.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.label.h;
import com.budejie.www.h.c;
import com.budejie.www.type.SearchItem;
import com.budejie.www.util.ah;
import com.budejie.www.util.ai;
import java.util.List;

public class b extends BaseAdapter {
    private List<SearchItem> a;
    private Context b;
    private LayoutInflater c = LayoutInflater.from(this.b);
    private boolean d;

    private class a {
        AsyncImageView a;
        ImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        View h;
        final /* synthetic */ b i;

        private a(b bVar) {
            this.i = bVar;
        }
    }

    public b(Context context, List<SearchItem> list) {
        this.b = context;
        this.a = list;
        this.d = ai.a(context) == 0;
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
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = this.c.inflate(R.layout.label_user_list_item, null);
            aVar2.a = (AsyncImageView) view.findViewById(R.id.portrait_image_view);
            aVar2.b = (ImageView) view.findViewById(R.id.sex_image_view);
            aVar2.c = (TextView) view.findViewById(R.id.rank_number_text_view);
            aVar2.d = (TextView) view.findViewById(R.id.user_name_text_view);
            aVar2.e = (TextView) view.findViewById(R.id.praise_count_text_view);
            aVar2.f = (TextView) view.findViewById(R.id.comment_text_view);
            aVar2.g = (TextView) view.findViewById(R.id.post_number_text_view);
            aVar2.h = view.findViewById(R.id.tag_view);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        a(aVar, i, view);
        return view;
    }

    private void a(a aVar, int i, View view) {
        if (aVar != null) {
            final SearchItem searchItem = (SearchItem) this.a.get(i);
            if (searchItem != null) {
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        com.budejie.www.util.a.a(this.b.b, searchItem.getId());
                    }
                });
                aVar.c.setText((i + 1) + "");
                h.a(this.b, aVar.c, i, this.d);
                String profileImageLarge = searchItem.getProfileImageLarge();
                if (TextUtils.isEmpty(profileImageLarge)) {
                    profileImageLarge = searchItem.getProfileImage();
                }
                aVar.b.setImageResource(c.a().b("f".equals(searchItem.getSex()) ? R.attr.label_sex_female_theme : R.attr.label_sex_male_theme));
                aVar.a.setPostAvatarImage(profileImageLarge);
                aVar.e.setText(a(searchItem.getPraiseCount()) + "个赞");
                aVar.d.setText(searchItem.getUsername());
                aVar.f.setText(a(searchItem.getCommentsCount()) + "评论");
                aVar.g.setText(a(searchItem.getPostsCount()) + "帖子");
            }
        }
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            parseInt = 0;
        }
        return ah.a(parseInt, this.b.getResources().getString(R.string.million_text));
    }
}
