package qsbk.app.fragments;

import android.os.Bundle;
import qsbk.app.Constants;

public class ContactFriendsFragment extends ContactQiuYouFragment {
    public ContactFriendsFragment() {
        super(Constants.REL_GET_FRIENDS, ContactFriendsFragment.class.getSimpleName());
    }

    public ContactFriendsFragment(Bundle bundle) {
        super(Constants.REL_GET_FRIENDS, ContactFriendsFragment.class.getSimpleName(), bundle);
    }
}
