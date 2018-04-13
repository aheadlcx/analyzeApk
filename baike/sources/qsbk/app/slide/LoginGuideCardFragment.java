package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.UserLoginGuideCard;

public class LoginGuideCardFragment extends BaseFragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        UserLoginGuideCard.getNewInstance(getActivity());
        return UserLoginGuideCard.getView(layoutInflater, null, viewGroup);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
