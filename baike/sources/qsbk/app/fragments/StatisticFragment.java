package qsbk.app.fragments;

import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;
import com.qiushibaike.statsdk.StatSDK;

public class StatisticFragment extends BaseFragment {
    private String a;

    public String getStatPageName() {
        if (this.a != null) {
            return this.a;
        }
        return getClass().getName();
    }

    public void setStatisticsEvent(String str) {
        this.a = str;
    }

    public void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(getActivity(), "LLLGV7Y72RGDIMUHII8Z");
    }

    public void onResume() {
        super.onResume();
        StatService.onPageStart(getActivity(), getStatPageName());
        StatSDK.onEvent(getActivity(), getStatPageName(), "resume");
    }

    public void onPause() {
        super.onPause();
        StatService.onPageEnd(getActivity(), getStatPageName());
    }

    public void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(getActivity());
    }
}
