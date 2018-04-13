package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.fragment.BaseFragment;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.NormalSpViewHolder;
import cn.tatagou.sdk.util.p;
import com.ali.auth.third.core.model.Constants;

public class e {
    public static void setItem(Activity activity, BaseFragment baseFragment, Item item, NormalSpViewHolder normalSpViewHolder) {
        setItem(activity, baseFragment, item, normalSpViewHolder, null, null);
    }

    public static void setItem(Activity activity, BaseFragment baseFragment, Item item, NormalSpViewHolder normalSpViewHolder, String str, String str2) {
        if (item != null) {
            if (p.isEmpty(item.getSellCount()) || "0".equals(item.getSellCount())) {
                normalSpViewHolder.mTvSellCount.setVisibility(8);
            } else {
                normalSpViewHolder.mTvSellCount.setVisibility(0);
                normalSpViewHolder.mTvSellCount.setText("已售" + item.getSellCount() + "件");
            }
            if (str == null) {
                str = item.getCoverImg();
            }
            if (baseFragment == null) {
                p.showNetImg(activity, str, normalSpViewHolder.mIvSession);
            } else {
                p.showNetImg(baseFragment, str, normalSpViewHolder.mIvSession);
            }
            normalSpViewHolder.mTvIconProduct.setText(activity.getResources().getString("TAOBAO".equals(item.getTaobaoType()) ? R.string.ttg_go_taobao : R.string.ttg_go_tianmao));
            normalSpViewHolder.mTvMoney.setText("￥" + (p.isEmpty(item.getFinalPrice()) ? "0" : item.getFinalPrice()));
            if (!p.isEmpty(item.getOriPrice())) {
                Object obj = "￥" + item.getOriPrice();
                CharSequence spannableString = new SpannableString(obj);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
                normalSpViewHolder.mTvOriginalPrice.setText(spannableString);
            }
            setBadges(item, normalSpViewHolder.mTvNoFree, normalSpViewHolder.mTvBadges);
            TextView textView;
            if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(item.getIsTodayOnline())) {
                textView = normalSpViewHolder.mTvMixTitle;
                if (!p.isEmpty(item.getTitle())) {
                    str2 = item.getTitle();
                }
                setImageText(activity, textView, str2);
                return;
            }
            textView = normalSpViewHolder.mTvMixTitle;
            if (!p.isEmpty(item.getTitle())) {
                str2 = item.getTitle();
            }
            textView.setText(str2);
        }
    }

    public static void setBadges(Item item, TextView textView, TextView textView2) {
        if (textView2 != null && textView != null) {
            if (p.isEmpty(item.getBadges())) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
            } else if ("包邮".equals(item.getBadges()) && item.getCoupon() == null) {
                textView2.setVisibility(8);
                textView.setVisibility(0);
                textView.setText(item.getBadges());
            } else {
                textView.setVisibility(8);
                if ("拍立减".equals(item.getBadges()) || item.getCoupon() != null) {
                    textView2.setVisibility(0);
                    textView2.setText(item.getBadges());
                    return;
                }
                textView2.setVisibility(8);
            }
        }
    }

    public static void setImageText(Activity activity, TextView textView, String str) {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(activity.getResources().getString(R.string.ttg_icon_new_today).concat("\t" + str));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF2738")), 0, 1, 33);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.0f), 0, 1, 33);
        textView.setText(spannableStringBuilder);
    }
}
