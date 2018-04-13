package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.room.utils.WealthRankImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class FansListAdapter extends BaseAdapter {
    private List<FansBean> a;
    private Context b;

    static class a {
        ImageView a;
        SimpleDraweeView b;
        TextView c;
        SimpleDraweeView d;
        TextView e;
        TextView f;

        a() {
        }
    }

    public FansListAdapter(Context context, List<FansBean> list) {
        this.b = context;
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        FansBean fansBean = (FansBean) this.a.get(i);
        if (view == null || !(view instanceof LinearLayout)) {
            view = View.inflate(this.b, R.layout.phone_room_fans_list_item, null);
            aVar = new a();
            aVar.a = (ImageView) view.findViewById(R.id.iv_fans_level);
            aVar.b = (SimpleDraweeView) view.findViewById(R.id.iv_fans_pic);
            aVar.d = (SimpleDraweeView) view.findViewById(R.id.iv_coin6rank);
            aVar.c = (TextView) view.findViewById(R.id.tv_name);
            aVar.e = (TextView) view.findViewById(R.id.tv_contribution);
            aVar.f = (TextView) view.findViewById(R.id.tv_fans_level);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.a.setVisibility(0);
            aVar.f.setVisibility(8);
            aVar.a.setBackgroundResource(R.drawable.rooms_third_cup);
        } else {
            aVar.a.setVisibility(8);
            aVar.f.setVisibility(0);
            if (i == 1 || i == 2) {
                aVar.f.setTextColor(this.b.getResources().getColor(R.color.deep_red));
            } else {
                aVar.f.setTextColor(this.b.getResources().getColor(R.color.deep_gray));
            }
            aVar.f.setText(String.valueOf(i + 1));
        }
        aVar.c.setText(fansBean.getUname());
        WealthRankImageUtils.setWealthImageView(fansBean.getUid(), Integer.parseInt(fansBean.getCoin6rank()), aVar.d);
        CharSequence contribution = fansBean.getContribution();
        if (TextUtils.isEmpty(contribution)) {
            contribution = fansBean.getMoney();
        }
        aVar.e.setText(contribution);
        aVar.b.setImageURI(Uri.parse(fansBean.getPicuser()));
        return view;
    }
}
