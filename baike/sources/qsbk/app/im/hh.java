package qsbk.app.im;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.QiuYouActivity;
import qsbk.app.utils.SharePreferenceUtils;

class hh implements OnClickListener {
    final /* synthetic */ IMMessageListFragment a;

    hh(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onClick(View view) {
        if (this.a.p()) {
            Intent intent = new Intent(this.a.getActivity(), QiuYouActivity.class);
            if (IMMessageListFragment.e > 0) {
                intent.putExtra("has_new_fans", true);
            }
            IMMessageListFragment.e = 0;
            SharePreferenceUtils.setSharePreferencesValue(MessageCountManager.NEWFANS_COUNT, 0);
            ActivityCompat.invalidateOptionsMenu(this.a.getActivity());
            if (this.a.getActivity() instanceof MainActivity) {
                ((MainActivity) this.a.getActivity()).updateIMMessageTips();
            }
            this.a.startActivity(intent);
            return;
        }
        this.a.m();
    }
}
