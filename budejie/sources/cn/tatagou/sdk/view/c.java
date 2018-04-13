package cn.tatagou.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$dimen;
import cn.tatagou.sdk.R$drawable;
import cn.tatagou.sdk.activity.TtgMainActivity;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.android.TtgSource;
import cn.tatagou.sdk.b.a;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.util.q;

public class c {
    public static void setWnlBottomHeight(Context context, LinearLayout linearLayout, TextView textView) {
        if (TtgSource.WNL.equals(TtgSDK.sSource) && !TtgMainActivity.sIsActShow && context != null) {
            if (linearLayout != null) {
                LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = (int) context.getResources().getDimension(R$dimen.ttg_wnl_backup_top);
                linearLayout.setLayoutParams(layoutParams);
            }
            if (textView != null) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams2.bottomMargin = (int) context.getResources().getDimension(R$dimen.ttg_wnl_main_bottom);
                textView.setLayoutParams(layoutParams2);
            }
        }
    }

    public static void setTitleHeight(Context context, RelativeLayout relativeLayout, boolean z) {
        TtgTitleBar instance = TtgTitleBar.getInstance();
        if (relativeLayout != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.height = p.dip2px(context, (float) instance.getHeight());
            relativeLayout.setLayoutParams(layoutParams);
            if (z) {
                relativeLayout.setBackgroundColor(instance.getBgColor());
            }
        }
    }

    public static void setTitleCenter(Context context, TextView textView, int i, boolean z) {
        TtgTitleBar instance = TtgTitleBar.getInstance();
        if (textView != null) {
            textView.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            if (instance.isTitleCenter()) {
                layoutParams.addRule(13, -1);
            } else if (z) {
                layoutParams.addRule(1, i);
            } else {
                layoutParams.addRule(9, -1);
                layoutParams.leftMargin = p.dip2px(context, 20.0f);
            }
            textView.setLayoutParams(layoutParams);
            textView.setTextSize((float) instance.getTitleSize());
            textView.setTextColor(instance.getTitleColor());
            if (instance.getTitleFont() != null) {
                textView.setTypeface(instance.getTitleFont());
            }
        }
    }

    public static void setSearchCenter(Context context, View view, int i, int i2, boolean z, boolean z2) {
        TtgTitleBar instance = TtgTitleBar.getInstance();
        if (view != null) {
            view.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (instance.isSearchCenter()) {
                layoutParams.addRule(13, -1);
            } else if (z) {
                layoutParams.addRule(1, i);
                layoutParams.addRule(0, i2);
            } else if (z2) {
                layoutParams.addRule(9, -1);
                layoutParams.addRule(0, i2);
                layoutParams.leftMargin = p.dip2px(context, 20.0f);
            } else {
                int windowWidth = p.getWindowWidth(context);
                layoutParams.width = windowWidth - (windowWidth / 3);
                layoutParams.addRule(13, -1);
                layoutParams.rightMargin = 0;
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setSearchIconCenter(View view, View view2, View view3, boolean z, Context context) {
        view.setVisibility(0);
        TtgTitleBar instance = TtgTitleBar.getInstance();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.addRule(15, -1);
        if (instance.isTitleCenter()) {
            if (!z) {
                layoutParams.addRule(9, -1);
                layoutParams.leftMargin = p.dip2px(context, 20.0f);
            } else if (view2.getVisibility() == 0) {
                layoutParams.addRule(0, getId(view2));
            } else if (view3.getVisibility() == 0) {
                layoutParams.addRule(0, getId(view3));
            } else {
                view.setPadding(0, 0, p.dip2px(context, 15.0f), 0);
                layoutParams.addRule(11, -1);
            }
        } else if (view2.getVisibility() == 0) {
            layoutParams.addRule(0, getId(view2));
        } else if (view3.getVisibility() == 0) {
            layoutParams.addRule(0, getId(view3));
            layoutParams.rightMargin = p.dip2px(context, 15.0f);
        } else {
            view.setPadding(0, 0, p.dip2px(context, 15.0f), 0);
            layoutParams.addRule(11, -1);
        }
        view.setLayoutParams(layoutParams);
    }

    public static boolean checkNetAndChooseSex(Activity activity, String str, View view, cn.tatagou.sdk.util.c cVar) {
        if (!p.isNetworkOpen(activity) || !TextUtils.isEmpty(str) || view == null) {
            return false;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ttg_linear_indentity_pop);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.ttg_linear_indentity);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.ttg_identity_bottom_root);
        q.onResetShapeThemeColor((LinearLayout) view.findViewById(R.id.ttg_idenity_relative_top), 0, 0, TtgConfig.getInstance().getThemeColor());
        String sexList = Config.getInstance().getSexList();
        if (TextUtils.isEmpty(sexList)) {
            sexList = "F,M,L";
        }
        String[] split = sexList.split(",");
        if (split.length == 0) {
            return false;
        }
        if (split.length == 1) {
            a.saveStr("ttgSex", split[0]);
            Config.getInstance().setSex(split[0]);
            return false;
        }
        if (split.length == 3 || split.length == 2) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.leftMargin = p.dip2px(activity, 25.0f);
            layoutParams.rightMargin = p.dip2px(activity, 25.0f);
            linearLayout.setLayoutParams(layoutParams);
        }
        for (String str2 : split) {
            View inflate = View.inflate(activity, R.layout.ttg_main_indentity_item, null);
            LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.ttg_linear_item);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.ttg_img_user);
            TextView textView = (TextView) inflate.findViewById(R.id.ttg_sex);
            TextView textView2 = (TextView) inflate.findViewById(R.id.ttg_description);
            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            if ("F".equals(str2)) {
                a(activity, textView, textView2, imageView, R$drawable.woman, R.string.ttg_beauty, R.string.ttg_beauty_des);
            }
            if ("M".equals(str2)) {
                a(activity, textView, textView2, imageView, R$drawable.man, R.string.ttg_handsome_guy, R.string.ttg_handsome_guy_des);
            }
            if ("L".equals(str2)) {
                a(activity, textView, textView2, imageView, R$drawable.lama, R.string.ttg_freaky, R.string.ttg_freaky_des);
            }
            linearLayout4.setOnClickListener(new c$1(linearLayout2, cVar, str2));
            linearLayout3.addView(inflate);
        }
        linearLayout2.setOnTouchListener(new c$2());
        linearLayout2.setVisibility(0);
        return true;
    }

    private static void a(Activity activity, TextView textView, TextView textView2, ImageView imageView, int i, int i2, int i3) {
        if (imageView != null) {
            imageView.setImageResource(i);
        }
        CharSequence string = p.getString(activity, i2);
        CharSequence string2 = p.getString(activity, i3);
        if (textView != null) {
            textView.setText(string);
        }
        if (textView2 != null) {
            textView2.setText(string2);
        }
    }

    public static int getId(View view) {
        return view != null ? view.getId() : 0;
    }

    public static void setTitleBar(Activity activity, View view, cn.tatagou.sdk.util.c cVar) {
        int i = 32;
        TtgTitleBar instance = TtgTitleBar.getInstance();
        TitleBar titleBar = new TitleBar();
        titleBar.setTitle(instance.getTitle());
        titleBar.setLeftIconShow(instance.isBackIconShown());
        titleBar.setCartIconShow(instance.isCartIconShown());
        titleBar.setSearchShown(instance.isSearchShown());
        titleBar.setSearchIconShown(instance.isSearchIconShown());
        titleBar.setSmallCircleShown(!a.getBoolean("mineShoppingHint"));
        titleBar.setTtgMain(true);
        titleBar.setBackIconRightPadding(instance.getBackIconSearchRightPadding());
        if (instance.isSearchShown()) {
            instance.setSearchIconShown(false);
        }
        if (instance.isBackIconShown() && !instance.isSearchIconRight()) {
            if (instance.isSearchShown()) {
                instance.setSearchIconShown(false);
            } else {
                instance.setSearchIconRight(true);
            }
        }
        if (instance.isCartIconShown()) {
            titleBar.setCartIconShow(instance.isCartIconShown());
            titleBar.setTvMineIconFontCode(p.isEmpty(instance.getCartIcon()) ? activity.getResources().getString(R.string.ttg_icon_me_cart_hastext) : TtgTitleBar.getInstance().getCartIcon());
            titleBar.setTvMineIconSize(instance.getCartIconSize() == 0 ? 32 : instance.getCartIconSize());
        }
        if (instance.isMyShoppingIconShown()) {
            titleBar.setRightIconShow(instance.isMyShoppingIconShown());
            titleBar.setTvRightIconfontCode(p.isEmpty(instance.getMyShoppingIcon()) ? p.getString(activity, R.string.ttg_icon_mine_cartIcon_hastext) : TtgTitleBar.getInstance().getMyShoppingIcon());
            titleBar.setTvRightIconSize(instance.getMyShoppingIconSize() == 0 ? 32 : instance.getMyShoppingIconSize());
        }
        if (instance.isSearchIconShown()) {
            titleBar.setSearchIconShown(instance.isSearchIconShown());
            titleBar.setTvSearchIconfontCode(p.isEmpty(instance.getSearchIcon()) ? p.getString(activity, R.string.ttg_icon_search_hastext) : TtgTitleBar.getInstance().getSearchIcon());
            if (instance.getSearchIconSize() != 0) {
                i = instance.getSearchIconSize();
            }
            titleBar.setTvSearchIconSize(i);
        }
        if (cVar != null) {
            cVar.onTitle(view, titleBar);
        }
    }
}
