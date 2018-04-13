package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.fragment.BaseFragment;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.NormalSpViewHolder;
import cn.tatagou.sdk.util.m;
import java.util.List;

public class f extends a<Item> {
    private static final String e = c.class.getSimpleName();
    private BaseFragment f;

    public f(Activity activity, List<Item> list, BaseFragment baseFragment) {
        super(activity, (List) list);
        this.f = baseFragment;
    }

    public void setItems(List<Item> list) {
        super.setItems(list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        NormalSpViewHolder normalSpViewHolder;
        if (view == null) {
            NormalSpViewHolder normalSpViewHolder2 = new NormalSpViewHolder();
            view = LayoutInflater.from(this.b).inflate(R.layout.ttg_session_item, viewGroup, false);
            normalSpViewHolder2.mLyText = (LinearLayout) view.findViewById(R.id.ly_text);
            normalSpViewHolder2.mIvBanner = (ImageView) view.findViewById(R.id.iv_banner);
            normalSpViewHolder2.mIvSession = (ImageView) view.findViewById(R.id.iv_session);
            normalSpViewHolder2.mTvMixTitle = (TextView) view.findViewById(R.id.ttg_tv_title);
            normalSpViewHolder2.mTvMoney = (TextView) view.findViewById(R.id.tv_money);
            normalSpViewHolder2.mTvOriginalPrice = (TextView) view.findViewById(R.id.tv_original_price);
            normalSpViewHolder2.mTvNoFree = (TextView) view.findViewById(R.id.tv_no_free);
            normalSpViewHolder2.mTvSellCount = (TextView) view.findViewById(R.id.tv_sellCount);
            normalSpViewHolder2.mTvFirstLine = (TextView) view.findViewById(R.id.tv_first_line);
            normalSpViewHolder2.mTvIconProduct = (TextView) view.findViewById(R.id.ttg_icon_product);
            normalSpViewHolder2.mTvBadges = (TextView) view.findViewById(R.id.ttg_tv_badges);
            normalSpViewHolder2.mTvListTitle = (LinearLayout) view.findViewById(R.id.tv_list_title);
            normalSpViewHolder2.mTv_line = (TextView) view.findViewById(R.id.ttg_line);
            view.setTag(normalSpViewHolder2);
            normalSpViewHolder = normalSpViewHolder2;
        } else {
            normalSpViewHolder = (NormalSpViewHolder) view.getTag();
        }
        ((TextView) view.findViewById(R.id.tv_test_text)).setVisibility(8);
        normalSpViewHolder.mTvListTitle.setVisibility(8);
        normalSpViewHolder.mTvListTitle.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        if (this.c != null && this.c.size() > 0 && this.c.size() >= i + 1) {
            final Item item = (Item) this.c.get(i);
            if (i > 0 && ((Item) this.c.get(i - 1)).getIsPriceChange() == 0 && item.getIsPriceChange() == 1) {
                normalSpViewHolder.mTvListTitle.setVisibility(0);
                normalSpViewHolder.mTv_line.setVisibility(8);
            }
            if (i == 0 && item.getIsPriceChange() == 1) {
                normalSpViewHolder.mTvListTitle.setVisibility(0);
                normalSpViewHolder.mTv_line.setVisibility(8);
                normalSpViewHolder.mTvFirstLine.setVisibility(0);
            }
            if (i == 0 && item.getIsPriceChange() == 0) {
                normalSpViewHolder.mTvFirstLine.setVisibility(0);
            }
            normalSpViewHolder.mIvBanner.setVisibility(8);
            normalSpViewHolder.mLyText.setVisibility(0);
            e.setItem(this.b, this.f, item, normalSpViewHolder);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ f b;

                public void onClick(View view) {
                    String str = null;
                    m.openGoodsDetails(this.b.b, item, null, str, "SEARCH", item.getTaobaoType());
                }
            });
        }
        return view;
    }
}
