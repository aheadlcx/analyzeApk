package cn.v6.sixrooms.hall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.ui.fragment.BaseFragment;
import cn.v6.sixrooms.ui.view.indicator.CommonNavigator;
import cn.v6.sixrooms.ui.view.indicator.IPagerNavigator;
import cn.v6.sixrooms.ui.view.indicator.ViewPagerHelper;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.widgets.phone.indicator.MagicIndicator;
import java.util.ArrayList;
import java.util.List;

public class HotFragment extends BaseFragment implements OnClickListener {
    private ag a;
    private List<String> b;
    private ViewPager c;

    public static HotFragment newInstance() {
        return new HotFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.phone_fragment_hot, viewGroup, false);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getView() != null) {
            ImageView imageView = (ImageView) getView().findViewById(R.id.backIv);
            FrameLayout frameLayout = (FrameLayout) getView().findViewById(R.id.categoryFl);
            MagicIndicator magicIndicator = (MagicIndicator) getView().findViewById(R.id.indicator);
            if (V6Coop.getInstance().isShowBack()) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
                layoutParams.leftMargin = DensityUtil.dip2px(15.0f);
                layoutParams.weight = 1.0f;
                magicIndicator.setLayoutParams(layoutParams);
            }
            this.c = (ViewPager) getView().findViewById(R.id.viewPager);
            imageView.setOnClickListener(this);
            frameLayout.setOnClickListener(this);
            List arrayList = new ArrayList();
            arrayList.add("");
            arrayList.add(CommonStrs.TYPE_MLIVE);
            arrayList.add(CommonStrs.TYPE_LOCATION);
            arrayList.add(CommonStrs.TYPE_MUSIC);
            arrayList.add(CommonStrs.TYPE_DANCE);
            arrayList.add(CommonStrs.TYPE_MC);
            arrayList.add(CommonStrs.TYPE_TALK);
            arrayList.add(CommonStrs.TYPE_MALE);
            this.b = arrayList;
            PagerAdapter sixRoomsPagerAdapter = new SixRoomsPagerAdapter(getChildFragmentManager(), this.b);
            this.c.setOffscreenPageLimit(1);
            this.c.setAdapter(sixRoomsPagerAdapter);
            IPagerNavigator commonNavigator = new CommonNavigator(getActivity());
            commonNavigator.setScrollPivotX(0.5f);
            commonNavigator.setAdapter(new ai(this));
            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, this.c);
            this.c.addOnPageChangeListener(new ak(this));
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.backIv) {
            getActivity().finish();
        } else if (view.getId() == R.id.categoryFl) {
            if (this.a == null) {
                this.a = new ag(getActivity(), this.b, new al(this));
            }
            this.a.show();
            StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HotFragment.class.getSimpleName()), StatisticCodeTable.MORE);
        }
    }
}
