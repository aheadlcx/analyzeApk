package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.fragment.BaseFragment;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.util.m;
import cn.tatagou.sdk.util.p;
import java.util.List;

public class i extends BaseAdapter {
    private static final String a = i.class.getSimpleName();
    private Activity b;
    private List<Item> c;
    private BaseFragment d;
    private String e;
    private String f;

    class a {
        LinearLayout a;
        ImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        LinearLayout i;
        LinearLayout j;
        ImageView k;
        TextView l;
        TextView m;
        TextView n;
        TextView o;
        TextView p;
        TextView q;
        final /* synthetic */ i r;

        a(i iVar) {
            this.r = iVar;
        }
    }

    public i(Activity activity, String str, String str2, List<Item> list, BaseFragment baseFragment) {
        this.b = activity;
        this.c = list;
        this.d = baseFragment;
        this.e = str2;
        this.f = str;
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        return (this.c.size() + 1) / 2;
    }

    public void setItems(List<Item> list) {
        if (list == null || list.size() == 0) {
            this.c.clear();
        } else {
            this.c = list;
        }
        notifyDataSetChanged();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.ttg_two_session_item, viewGroup, false);
            aVar2.a = (LinearLayout) view.findViewById(R.id.rl_first);
            aVar2.i = (LinearLayout) view.findViewById(R.id.rl_second);
            aVar2.j = (LinearLayout) view.findViewById(R.id.rl_info2);
            aVar2.b = (ImageView) view.findViewById(R.id.iv_session);
            aVar2.k = (ImageView) view.findViewById(R.id.iv_session_2);
            aVar2.c = (TextView) view.findViewById(R.id.ttg_tv_mix_title);
            aVar2.l = (TextView) view.findViewById(R.id.ttg_tv_mix_title2);
            aVar2.d = (TextView) view.findViewById(R.id.tv_money);
            aVar2.m = (TextView) view.findViewById(R.id.tv_money_2);
            aVar2.e = (TextView) view.findViewById(R.id.tv_original_price);
            aVar2.n = (TextView) view.findViewById(R.id.tv_original_price_2);
            aVar2.f = (TextView) view.findViewById(R.id.tv_no_free);
            aVar2.o = (TextView) view.findViewById(R.id.tv_no_free_2);
            aVar2.g = (TextView) view.findViewById(R.id.ttg_tv_sellCount);
            aVar2.p = (TextView) view.findViewById(R.id.ttg_tv_sellCount_2);
            aVar2.h = (TextView) view.findViewById(R.id.ttg_tv_badges);
            aVar2.q = (TextView) view.findViewById(R.id.ttg_tv_badges_2);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        final Item item = (Item) this.c.get(i * 2);
        a(item, aVar.b, aVar.c, aVar.d, aVar.f, aVar.e, aVar.g, aVar.h);
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i b;

            public void onClick(View view) {
                m.openGoodsDetails(this.b.b, item, this.b.f, this.b.e, "SP", item.getTaobaoType());
            }
        });
        if ((i * 2) + 1 < this.c.size()) {
            item = (Item) this.c.get((i * 2) + 1);
            aVar.j.setVisibility(0);
            aVar.k.setVisibility(0);
            aVar.i.setVisibility(0);
            a(item, aVar.k, aVar.l, aVar.m, aVar.o, aVar.n, aVar.p, aVar.q);
            aVar.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i b;

                public void onClick(View view) {
                    m.openGoodsDetails(this.b.b, item, this.b.f, this.b.e, "SP", item.getTaobaoType());
                }
            });
        } else {
            aVar.j.setVisibility(8);
            aVar.k.setVisibility(8);
            aVar.i.setVisibility(4);
        }
        return view;
    }

    private void a(Item item, final ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        p.showNetImg(this.d, item.getCoverImg(), imageView);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ i b;

            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                layoutParams.width = imageView.getWidth();
                layoutParams.height = imageView.getWidth();
                imageView.setLayoutParams(layoutParams);
            }
        });
        if (item.getTitle() != null) {
            textView.setText(item.getTitle());
        }
        if (item.getFinalPrice() != null) {
            textView2.setText("￥" + item.getFinalPrice());
        }
        if (!p.isEmpty(item.getOriPrice())) {
            Object obj = "￥" + item.getOriPrice();
            CharSequence spannableString = new SpannableString(obj);
            spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
            textView4.setText(spannableString);
        }
        if (p.isEmpty(item.getSellCount())) {
            textView5.setVisibility(8);
        } else {
            textView5.setVisibility(0);
            textView5.setText(item.getSellCount() + "件已售");
        }
        e.setBadges(item, textView3, textView6);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            super.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
