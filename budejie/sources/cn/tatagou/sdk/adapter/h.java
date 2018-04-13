package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.fragment.BaseFragment;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.NormalSpViewHolder;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.m;
import cn.tatagou.sdk.util.p;
import java.util.List;

public class h extends a<Special> {
    private static final String e = h.class.getSimpleName();
    private String f;
    private BaseFragment g;
    private int h = 0;
    private int i;
    private c j;
    private boolean k;
    private String l;
    private String m;
    private boolean n = true;
    private int o;

    public void setClickCallBack(c cVar) {
        this.j = cVar;
    }

    public void setRcmmPosition(int i) {
        this.i = i;
    }

    public void setRcmmFinshText(String str, boolean z) {
        this.m = str;
        this.n = z;
    }

    public void setSpHintText(boolean z, String str) {
        this.k = z;
        this.l = str;
    }

    public h(Activity activity, List<Special> list, String str, BaseFragment baseFragment) {
        super(activity, (List) list);
        this.f = str;
        this.g = baseFragment;
        this.h = p.getWindowWidth(activity);
        this.o = (int) (0.37878788f * ((float) this.h));
    }

    public void setItems(List<Special> list) {
        super.setItems(list);
    }

    public void setItems(List<Special> list, String str) {
        super.setItems(list);
        this.f = str;
    }

    public void addItems(List<Special> list) {
        super.addItems(list);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        NormalSpViewHolder normalSpViewHolder;
        if (view == null) {
            normalSpViewHolder = new NormalSpViewHolder();
            view = LayoutInflater.from(this.b).inflate(R.layout.ttg_session_item, viewGroup, false);
            a(normalSpViewHolder, view);
            view.setTag(normalSpViewHolder);
        } else {
            normalSpViewHolder = (NormalSpViewHolder) view.getTag();
        }
        View findViewById = view.findViewById(R.id.ttg_tv_list_title_line);
        final Special special = (Special) this.c.get(i);
        if (special != null) {
            if (i == 0 && this.k) {
                normalSpViewHolder.mTvHintText.setVisibility(0);
                findViewById.setVisibility(0);
                normalSpViewHolder.mTvHintText.setText(p.isEmpty(this.l) ? "每天百款新品，10点更新" : this.l);
            } else {
                normalSpViewHolder.mTvHintText.setVisibility(8);
                findViewById.setVisibility(8);
            }
            if (this.i <= 0 || i != this.i - 1) {
                normalSpViewHolder.mTvRcmmRefresh.setVisibility(8);
                normalSpViewHolder.mTvFirstLine.setVisibility(0);
            } else {
                normalSpViewHolder.mTvFirstLine.setVisibility(8);
                normalSpViewHolder.mTvRcmmRefresh.setVisibility(0);
                normalSpViewHolder.mTvRcmmRefresh.setTextColor(TtgConfig.getInstance().getRmTextColor());
                normalSpViewHolder.mTvRcmmRefresh.setText(p.isEmpty(this.m) ? this.b.getResources().getString(R.string.ttg_icon_refresh) : this.m);
                normalSpViewHolder.mTvRcmmRefresh.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.j != null && this.a.n) {
                            this.a.j.onRcmmSpClickRefresh(view);
                        }
                    }
                });
            }
            if (special.getItem() == null) {
                normalSpViewHolder.mLyBanner.setVisibility(0);
                normalSpViewHolder.mLyText.setVisibility(8);
                normalSpViewHolder.mIvActIcon.setVisibility(8);
                p.onLoadBannerImage(this.b, normalSpViewHolder.mIvBanner, special, this.h);
                if (this.h != -1) {
                    LayoutParams layoutParams = (LayoutParams) normalSpViewHolder.mIvBanner.getLayoutParams();
                    layoutParams.width = this.h;
                    if (special.getCoverImgScale() > 0.0d) {
                        layoutParams.height = (int) (((double) this.h) / special.getCoverImgScale());
                    }
                    normalSpViewHolder.mIvBanner.setLayoutParams(layoutParams);
                }
            } else {
                if (TtgSDK.isDebug) {
                    TextView textView = (TextView) view.findViewById(R.id.tv_test_text);
                    textView.setText(p.isEmpty(special.getTestText()) ? "暂无类目推荐" : special.getTestText());
                    textView.setVisibility(8);
                }
                if (p.isEmpty(special.getSubImage())) {
                    normalSpViewHolder.mIvActIcon.setVisibility(8);
                } else {
                    normalSpViewHolder.mIvActIcon.setVisibility(0);
                    if (this.g == null) {
                        p.showNetImg(this.b, special.getSubImage(), normalSpViewHolder.mIvActIcon);
                    } else {
                        p.showNetImg(this.g, special.getSubImage(), normalSpViewHolder.mIvActIcon);
                    }
                }
                List<String> tags = special.getTags();
                normalSpViewHolder.mTvLabelIcon.setVisibility(8);
                if (tags != null && tags.size() > 0) {
                    for (String str : tags) {
                        if (!p.isEmpty(str)) {
                            normalSpViewHolder.mTvLabelIcon.setText(str);
                            normalSpViewHolder.mTvLabelIcon.setVisibility(0);
                            break;
                        }
                    }
                }
                if (this.o >= 0) {
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) normalSpViewHolder.mIvSession.getLayoutParams();
                    layoutParams2.width = this.o;
                    layoutParams2.height = this.o;
                    normalSpViewHolder.mIvSession.setLayoutParams(layoutParams2);
                }
                normalSpViewHolder.mLyBanner.setVisibility(8);
                normalSpViewHolder.mLyText.setVisibility(0);
                e.setItem(this.b, this.g, special.getItem(), normalSpViewHolder, special.getCoverImg(), special.getTitle());
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ h c;

                public void onClick(View view) {
                    if ("BANNER".equals(special.getIsBanner()) || "ITEMS".equals(special.getIsBanner())) {
                        String str = (this.c.i <= 0 || i > this.c.i - 1) ? null : "RM";
                        m.openSpecialList(this.c.b, special.getId(), special.getMarker(), this.c.f, special.getTitle(), str);
                    } else if ("URL".equals(special.getIsBanner()) || "TBURL".equals(special.getIsBanner())) {
                        m.openUrlH5(this.c.b, special);
                    } else if ("ITEM".equals(special.getIsBanner())) {
                        Item item = special.getItem();
                        if (item != null) {
                            String str2 = (this.c.i <= 0 || i > this.c.i - 1) ? "HOME" : "RM";
                            b.spStatEvent(special.getId(), special.getMarker(), str2);
                            m.openGoodsDetails(this.c.b, item, special.getId(), this.c.f, str2, item.getTaobaoType());
                        }
                    }
                }
            });
        }
        return view;
    }

    private void a(NormalSpViewHolder normalSpViewHolder, View view) {
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
        normalSpViewHolder.mIvActIcon = (ImageView) view.findViewById(R.id.iv_act_icon);
        normalSpViewHolder.mLyBanner = (LinearLayout) view.findViewById(R.id.ttg_ly_banner);
        normalSpViewHolder.mTvLabelIcon = (TextView) view.findViewById(R.id.tv_label_icon);
        normalSpViewHolder.mTvBadges = (TextView) view.findViewById(R.id.ttg_tv_badges);
        normalSpViewHolder.mTvRcmmRefresh = (TextView) view.findViewById(R.id.ttg_tv_rcmm_refresh);
        normalSpViewHolder.mTvHintText = (TextView) view.findViewById(R.id.ttg_tv_list_title);
    }
}
