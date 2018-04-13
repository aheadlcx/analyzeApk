package qsbk.app.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.WalletActivity;
import qsbk.app.im.MessageCountManager;
import qsbk.app.utils.SharePreferenceUtils;

class gq implements OnItemClickListener {
    final /* synthetic */ MyProfileFragment a;

    gq(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z = true;
        c cVar = (c) this.a.e.get(i);
        if (cVar != null) {
            switch (cVar.m) {
                case 1:
                    this.a.d();
                    return;
                case 2:
                    this.a.f();
                    return;
                case 3:
                    if (MyProfileFragment.newFans <= 0) {
                        z = false;
                    }
                    MyProfileFragment.newFans = 0;
                    SharePreferenceUtils.remove(MessageCountManager.NEWFANS_COUNT);
                    this.a.d.notifyDataSetChanged();
                    if (this.a.getActivity() instanceof MainActivity) {
                        ((MainActivity) this.a.getActivity()).setHasClickMyProfileFragment();
                    }
                    this.a.b(z);
                    return;
                case 5:
                    this.a.h();
                    return;
                case 7:
                    this.a.i();
                    return;
                case 8:
                    this.a.a(true);
                    return;
                case 9:
                    this.a.a(false);
                    return;
                case 12:
                    if (this.a.r) {
                        cVar.o = false;
                        this.a.d.notifyDataSetChanged();
                        SharePreferenceUtils.remove("medal_tips");
                    }
                    this.a.g();
                    return;
                case 13:
                    if (QsbkApp.currentUser == null) {
                        this.a.getActivity().startActivity(new Intent(this.a.getActivity(), ActionBarLoginActivity.class));
                        return;
                    }
                    WalletActivity.launch(this.a.getActivity());
                    return;
                default:
                    return;
            }
        }
    }
}
