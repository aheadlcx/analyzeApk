package qsbk.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.IMChatingUrlContentDisplayActivity;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;

class jt implements OnClickListener {
    final /* synthetic */ QiuyouCircleFragment a;

    jt(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onClick(View view) {
        this.a.a.setVisibility(8);
        this.a.a.setOnClickListener(null);
        Intent intent = new Intent();
        intent.setClass(this.a.getActivity(), IMChatingUrlContentDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", SplashAdManager.mAnnouncement.link);
        intent.putExtras(bundle);
        this.a.getActivity().startActivity(intent);
        SharePreferenceUtils.remove(SplashAdManager.ANNOUNCEMENT_STRING);
        SplashAdManager.mAnnouncement = null;
    }
}
