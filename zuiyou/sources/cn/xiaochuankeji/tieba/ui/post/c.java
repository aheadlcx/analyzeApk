package cn.xiaochuankeji.tieba.ui.post;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.htjyb.b.a.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class c extends BaseAdapter {
    private Context a;
    private d<Member> b;

    static class a {
        WebImageView a;
        TextView b;
        TextView c;
        TextView d;

        a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public c(Context context, d<Member> dVar) {
        this.a = context;
        this.b = dVar;
    }

    public int getCount() {
        return this.b.itemCount();
    }

    public Member a(int i) {
        return (Member) this.b.itemAt(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.a).inflate(R.layout.view_item_member_preview, viewGroup, false);
            aVar2.a = (WebImageView) view.findViewById(R.id.pv_avatar);
            aVar2.b = (TextView) view.findViewById(R.id.tv_name);
            aVar2.d = (TextView) view.findViewById(R.id.label_sign);
            aVar2.c = (TextView) view.findViewById(R.id.tvLikeCount);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        Member a = a(i);
        aVar.a.setWebImage(b.a(a.getId(), a.getAvatarID()));
        aVar.b.setText(a.getName());
        TextView textView = aVar.b;
        int i2 = a.isRegistered() ? a.isFemale() ? R.drawable.personal_girls_s : R.drawable.personal_boy_s : 0;
        c.a.b.a(textView, 0, 0, i2, 0);
        if (TextUtils.isEmpty(a.getSign())) {
            aVar.d.setVisibility(8);
        } else {
            aVar.d.setVisibility(0);
            aVar.d.setText(a.getSign());
        }
        i2 = a.getLiken();
        if (i2 > 1) {
            aVar.c.setText(String.valueOf(i2) + " 个顶");
        } else {
            aVar.c.setVisibility(8);
        }
        return view;
    }
}
