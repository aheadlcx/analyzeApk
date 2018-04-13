package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RankingBean;
import cn.v6.sixrooms.bean.TotalRankingBean;
import java.util.List;

public class RankingFragmentAdapter extends BaseAdapter {
    int a = 0;
    private LayoutInflater b = null;
    private List<TotalRankingBean> c;
    private Resources d;

    private static class a {
        LinearLayout a;
        TextView b;
        ImageView c;
        ImageView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        TextView i;
        TextView j;

        private a() {
        }
    }

    public RankingFragmentAdapter(Context context, List<TotalRankingBean> list) {
        this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        this.c = list;
        this.d = context.getResources();
    }

    public void setTitle(int i) {
        this.a = i;
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        Drawable drawable;
        if (view == null) {
            view = this.b.inflate(R.layout.phone_ranking_fragment_adapter, null);
            a aVar2 = new a();
            aVar2.a = (LinearLayout) view.findViewById(R.id.star_rank_titel_ll);
            aVar2.b = (TextView) view.findViewById(R.id.star_rank_title_tv);
            aVar2.c = (ImageView) view.findViewById(R.id.star_rank_no1_num_iv);
            aVar2.d = (ImageView) view.findViewById(R.id.star_rank_no1_head_portrait_iv);
            aVar2.e = (TextView) view.findViewById(R.id.star_rank_no1_nickname_tv);
            aVar2.f = (TextView) view.findViewById(R.id.star_rank_no1_rid_tv);
            aVar2.g = (TextView) view.findViewById(R.id.star_rank_no2_nickname_tv);
            aVar2.h = (TextView) view.findViewById(R.id.star_rank_no2_rid_tv);
            aVar2.i = (TextView) view.findViewById(R.id.star_rank_no3_nickname_tv);
            aVar2.j = (TextView) view.findViewById(R.id.star_rank_no3_rid_tv);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        TotalRankingBean totalRankingBean = (TotalRankingBean) this.c.get(i);
        aVar.e.setText(((RankingBean) totalRankingBean.getBeans().get(0)).getUsername());
        aVar.f.setText("(" + ((RankingBean) totalRankingBean.getBeans().get(0)).getRid() + ")");
        aVar.g.setText(((RankingBean) totalRankingBean.getBeans().get(1)).getUsername());
        aVar.h.setText("(" + ((RankingBean) totalRankingBean.getBeans().get(1)).getRid() + ")");
        aVar.i.setText(((RankingBean) totalRankingBean.getBeans().get(2)).getUsername());
        aVar.j.setText("(" + ((RankingBean) totalRankingBean.getBeans().get(2)).getRid() + ")");
        switch (i) {
            case 0:
                aVar.a.setBackgroundResource(R.drawable.phone_ranking_star_bg_shape);
                drawable = this.d.getDrawable(R.drawable.phone_rank_star_rank_title);
                aVar.b.setText("明星榜");
                aVar.c.setImageResource(R.drawable.phone_rank_star_rank_no1_num);
                aVar.e.setTextColor(this.d.getColor(R.color.rank_star_title_bg));
                break;
            case 1:
                aVar.a.setBackgroundResource(R.drawable.phone_ranking_wealth_bg_shape);
                drawable = this.d.getDrawable(R.drawable.phone_rank_wealth_rank_title);
                aVar.b.setText("富豪榜");
                aVar.c.setImageResource(R.drawable.phone_rank_wealth_rank_no1_num);
                aVar.e.setTextColor(this.d.getColor(R.color.rank_wealth_title_bg));
                break;
            case 2:
                aVar.a.setBackgroundResource(R.drawable.phone_ranking_popularity_bg_shape);
                drawable = this.d.getDrawable(R.drawable.phone_rank_popularity_rank_title);
                aVar.b.setText("人气榜");
                aVar.c.setImageResource(R.drawable.phone_rank_popularity_rank_no1_num);
                aVar.e.setTextColor(this.d.getColor(R.color.rank_popularity_title_bg));
                break;
            case 3:
                aVar.a.setBackgroundResource(R.drawable.phone_ranking_song_bg_shape);
                drawable = this.d.getDrawable(R.drawable.phone_rank_song_rank_title);
                aVar.b.setText("点歌榜");
                aVar.c.setImageResource(R.drawable.phone_rank_song_rank_no1_num);
                aVar.e.setTextColor(this.d.getColor(R.color.rank_song_title_bg));
                break;
            default:
                drawable = null;
                break;
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            aVar.b.setCompoundDrawables(null, drawable, null, null);
        }
        return view;
    }
}
