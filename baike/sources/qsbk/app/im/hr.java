package qsbk.app.im;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;

class hr implements OnClickListener {
    final /* synthetic */ IMMessageListFragment a;

    hr(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onClick(View view) {
        this.a.a.setVisibility(8);
        Intent intent = new Intent();
        intent.setClass(this.a.getActivity(), IMChatingUrlContentDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", SplashAdManager.mAnnouncement.link);
        intent.putExtras(bundle);
        this.a.getActivity().startActivity(intent);
        SharePreferenceUtils.remove(SplashAdManager.ANNOUNCEMENT_STRING);
        SplashAdManager.mAnnouncement = null;
        this.a.a.setOnClickListener(null);
    }
}
