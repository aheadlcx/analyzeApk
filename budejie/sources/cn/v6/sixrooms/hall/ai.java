package cn.v6.sixrooms.hall;

import android.content.Context;
import android.graphics.Color;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.ui.view.indicator.CommonNavigatorAdapter;
import cn.v6.sixrooms.ui.view.indicator.IPagerIndicator;
import cn.v6.sixrooms.ui.view.indicator.IPagerTitleView;
import cn.v6.sixrooms.ui.view.indicator.LinePagerIndicator;
import cn.v6.sixrooms.ui.view.indicator.SimplePagerTitleView;
import cn.v6.sixrooms.utils.DensityUtil;

final class ai extends CommonNavigatorAdapter {
    final /* synthetic */ HotFragment a;

    ai(HotFragment hotFragment) {
        this.a = hotFragment;
    }

    public final int getCount() {
        return HotFragment.a(this.a).size();
    }

    public final IPagerTitleView getTitleView(Context context, int i) {
        IPagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setTextNormalColor(Color.parseColor("#333333"));
        simplePagerTitleView.setTextSelectedColor(Color.parseColor("#fa3e3e"));
        simplePagerTitleView.setText(SixRoomsUtils.parseTypeToTitle((String) HotFragment.a(this.a).get(i)));
        simplePagerTitleView.setTextSize(14.0f);
        simplePagerTitleView.setPadding(26, 0, 26, 0);
        simplePagerTitleView.setOnClickListener(new aj(this, i));
        return simplePagerTitleView;
    }

    public final IPagerIndicator getIndicator(Context context) {
        IPagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(2);
        linePagerIndicator.setLineWidth((float) DensityUtil.dip2px(24.0f));
        linePagerIndicator.setLineHeight((float) DensityUtil.dip2px(2.5f));
        linePagerIndicator.setRoundRadius((float) DensityUtil.dip2px(2.0f));
        linePagerIndicator.setColors(Integer.valueOf(Color.parseColor("#fa3e3e")));
        linePagerIndicator.setYOffset((float) DensityUtil.dip2px(10.0f));
        return linePagerIndicator;
    }
}
