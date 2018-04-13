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

public class c extends a<Item> {
    private static final String e = c.class.getSimpleName();
    private BaseFragment f;

    public c(Activity activity, List<Item> list, BaseFragment baseFragment) {
        super(activity, (List) list);
        this.f = baseFragment;
    }

    public void setItems(List<Item> list) {
        super.setItems(list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        NormalSpViewHolder normalSpViewHolder;
        if (view == null) {
            normalSpViewHolder = new NormalSpViewHolder();
            view = LayoutInflater.from(this.b).inflate(R.layout.ttg_session_item, viewGroup, false);
            normalSpViewHolder.mLyText = (LinearLayout) view.findViewById(R.id.ly_text);
            normalSpViewHolder.mIvBanner = (ImageView) view.findViewById(R.id.iv_banner);
            normalSpViewHolder.mIvSession = (ImageView) view.findViewById(R.id.iv_session);
            normalSpViewHolder.mTvMixTitle = (TextView) view.findViewById(R.id.ttg_tv_title);
            normalSpViewHolder.mTvMoney = (TextView) view.findViewById(R.id.tv_money);
            normalSpViewHolder.mTvOriginalPrice = (TextView) view.findViewById(R.id.tv_original_price);
            normalSpViewHolder.mTvNoFree = (TextView) view.findViewById(R.id.tv_no_free);
            normalSpViewHolder.mTvSellCount = (TextView) view.findViewById(R.id.tv_sellCount);
            normalSpViewHolder.mTvFirstLine = (TextView) view.findViewById(R.id.tv_first_line);
            normalSpViewHolder.mTvIconProduct = (TextView) view.findViewById(R.id.ttg_icon_product);
            normalSpViewHolder.mTvBadges = (TextView) view.findViewById(R.id.ttg_tv_badges);
            view.setTag(normalSpViewHolder);
        } else {
            normalSpViewHolder = (NormalSpViewHolder) view.getTag();
        }
        final Item item = (Item) this.c.get(i);
        normalSpViewHolder.mIvBanner.setVisibility(8);
        normalSpViewHolder.mLyText.setVisibility(0);
        e.setItem(this.b, this.f, item, normalSpViewHolder);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                if (item != null) {
                    this.b.c.remove(item);
                    this.b.c.add(0, item);
                    m.openGoodsDetails(this.b.b, item, "FP", item.getTaobaoType());
                }
            }
        });
        return view;
    }
}
