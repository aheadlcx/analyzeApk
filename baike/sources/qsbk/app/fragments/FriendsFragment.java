package qsbk.app.fragments;

import android.os.Bundle;
import qsbk.app.Constants;

public class FriendsFragment extends QiuYouFragment {
    public FriendsFragment() {
        super(Constants.REL_GET_FRIENDS, FriendsFragment.class.getSimpleName());
    }

    public FriendsFragment(Bundle bundle) {
        super(Constants.REL_GET_FRIENDS, FriendsFragment.class.getSimpleName(), bundle);
    }
}
