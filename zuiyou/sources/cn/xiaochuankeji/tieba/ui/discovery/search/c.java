package cn.xiaochuankeji.tieba.ui.discovery.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class c extends BaseAdapter {
    private Context a;
    private cn.htjyb.b.a.a<? extends Member> b;

    class a {
        TextView a;
        TextView b;
        WebImageView c;
        ImageView d;
        ImageView e;
        final /* synthetic */ c f;

        a(c cVar) {
            this.f = cVar;
        }
    }

    public c(Context context, cn.htjyb.b.a.a<? extends Member> aVar) {
        this.a = context;
        this.b = aVar;
    }

    public int getCount() {
        return this.b.itemCount();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        int i2;
        Member member = (Member) this.b.itemAt(i);
        if (view == null) {
            a aVar2 = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.view_item_search_member, viewGroup, false);
            aVar2.c = (WebImageView) view.findViewById(R.id.cover_pv);
            aVar2.a = (TextView) view.findViewById(R.id.tv_name);
            aVar2.b = (TextView) view.findViewById(R.id.tvBrief);
            aVar2.d = (ImageView) view.findViewById(R.id.iv_talent);
            aVar2.e = (ImageView) view.findViewById(R.id.iv_official_search);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c.setWebImage(b.a(member.getId(), member.getAvatarID()));
        aVar.a.setText(member.getName());
        c.a.b.a(aVar.a, 0, 0, member.getGender() == 2 ? R.drawable.personal_girls_s : R.drawable.personal_boy_s, 0);
        final Medal medal = member.getMedal();
        if (member.isOfficial() || medal == null) {
            aVar.d.setVisibility(8);
        } else {
            aVar.d.setVisibility(0);
            if (medal.original == 2) {
                aVar.d.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
            } else if (medal.original == 1) {
                aVar.d.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
            } else if (medal.original == 3) {
                aVar.d.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
            } else {
                aVar.d.setVisibility(8);
            }
            aVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c c;

                public void onClick(View view) {
                    new d(this.c.a, medal).a(aVar.d).show();
                }
            });
        }
        ImageView imageView = aVar.e;
        if (member.isOfficial()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.d(this.a.a);
            }
        });
        if (TextUtils.isEmpty(member.getSign())) {
            aVar.b.setVisibility(8);
        } else {
            aVar.b.setVisibility(0);
            aVar.b.setText(member.getSign());
        }
        return view;
    }
}
