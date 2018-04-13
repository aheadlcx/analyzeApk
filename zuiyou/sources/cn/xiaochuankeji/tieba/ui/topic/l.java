package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.c.d;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.topic.TopicInvolvedRankMemberList;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class l extends BaseAdapter {
    private Context a;
    private TopicInvolvedRankMemberList b;

    static class a {
        WebImageView a;
        ImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        View g;

        a() {
        }
    }

    public l(Context context, TopicInvolvedRankMemberList topicInvolvedRankMemberList) {
        this.a = context;
        this.b = topicInvolvedRankMemberList;
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
        d dVar = (d) this.b.itemAt(i);
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.a).inflate(R.layout.view_item_member_rank, viewGroup, false);
            aVar2.a = (WebImageView) view.findViewById(R.id.pv_avatar);
            aVar2.b = (ImageView) view.findViewById(R.id.ivCrown);
            aVar2.c = (TextView) view.findViewById(R.id.tv_name);
            aVar2.d = (TextView) view.findViewById(R.id.tvValueCount);
            aVar2.e = (TextView) view.findViewById(R.id.tvPostCommentCount);
            aVar2.f = (TextView) view.findViewById(R.id.tvSort);
            aVar2.g = view.findViewById(R.id.admin_flag);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setWebImage(b.a(dVar.a, dVar.a()));
        aVar.c.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(dVar.b));
        c.a.b.a(aVar.c, 0, 0, dVar.c == 2 ? R.drawable.personal_girls_s : R.drawable.personal_boy_s, 0);
        if (dVar.e == 1 || dVar.e == 2 || dVar.e == 3) {
            aVar.b.setVisibility(0);
        } else {
            aVar.b.setVisibility(4);
        }
        aVar.e.setText(String.valueOf(dVar.f) + "个帖子  |  " + String.valueOf(dVar.g) + "条评论");
        aVar.d.setText(String.valueOf(dVar.h) + " 个顶");
        if (dVar.d > 0) {
            aVar.f.setVisibility(0);
            aVar.f.setText(String.valueOf(dVar.d));
            TextPaint paint = aVar.f.getPaint();
            if (dVar.d > 3) {
                paint.setFakeBoldText(false);
            } else {
                paint.setFakeBoldText(true);
            }
        } else {
            aVar.f.setVisibility(4);
        }
        if (1 == dVar.i) {
            aVar.g.setVisibility(0);
        } else {
            aVar.g.setVisibility(4);
        }
        return view;
    }
}
