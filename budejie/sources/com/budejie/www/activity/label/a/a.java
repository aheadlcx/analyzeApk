package com.budejie.www.activity.label.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.h.c;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchItem$TypeEnum;
import java.util.List;

public class a extends Adapter<a> {
    private List<SearchItem> a;
    private Context b;
    private LayoutInflater c;
    private String d;

    public class a extends ViewHolder {
        AsyncImageView a;
        TextView b;
        final /* synthetic */ a c;

        a(a aVar, View view) {
            this.c = aVar;
            super(view);
            this.a = (AsyncImageView) view.findViewById(R.id.round_image_view);
            this.b = (TextView) view.findViewById(R.id.user_name_text_view);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a(Context context, List<SearchItem> list, String str) {
        this.a = list;
        this.b = context;
        this.c = LayoutInflater.from(context);
        this.d = str;
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(this, this.c.inflate(R.layout.label_moderator_item, viewGroup, false));
    }

    public void a(a aVar, int i) {
        final SearchItem searchItem = (SearchItem) this.a.get(i);
        if (searchItem != null) {
            aVar.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (searchItem.getType() == SearchItem$TypeEnum.ADD_MODERATOR.getValue()) {
                        com.budejie.www.util.a.a(this.b.b, this.b.d, true, this.b.a);
                    } else {
                        com.budejie.www.util.a.a(this.b.b, searchItem.getId());
                    }
                }
            });
            if (searchItem.getType() == SearchItem$TypeEnum.ADD_MODERATOR.getValue()) {
                aVar.a.setImageResource(c.a().b(R.attr.label_apply_moderator_theme));
                aVar.b.setText(R.string.lable_apply_deputy_moderator_text);
                return;
            }
            aVar.a.setPostAvatarImage(searchItem.getProfileImageLarge());
            aVar.b.setText(searchItem.getUsername());
        }
    }

    public int getItemCount() {
        return com.budejie.www.goddubbing.c.a.a(this.a) ? 0 : this.a.size();
    }
}
