package qsbk.app.nearby.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.R;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.widget.PersonalInfoView;

public class OneProfileFragment extends BaseFragment {
    public static final String KEY_USER = "key_user";
    private NearbyUser a;

    public static final OneProfileFragment newInstance(NearbyUser nearbyUser) {
        OneProfileFragment oneProfileFragment = new OneProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_USER, nearbyUser);
        oneProfileFragment.setArguments(bundle);
        return oneProfileFragment;
    }

    public NearbyUser getBindUser() {
        return this.a;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getArguments() != null ? (NearbyUser) getArguments().getSerializable(KEY_USER) : null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        PersonalInfoView personalInfoView = (PersonalInfoView) layoutInflater.inflate(R.layout.layout_nearby_user_info, viewGroup, false);
        personalInfoView.setView(this.a, 1);
        return personalInfoView;
    }
}
