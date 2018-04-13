package qsbk.app.fragments;

import qsbk.app.Constants;
import qsbk.app.live.LivePullLauncher;

public class LiveFoundFragment extends BaseLiveTabFragment {
    public String getUrl() {
        return String.format(Constants.LIVE_FOUND, new Object[]{Integer.valueOf(30), Integer.valueOf(this.a)});
    }

    protected String a() {
        return LivePullLauncher.STSOURCE_discoverlist;
    }
}
