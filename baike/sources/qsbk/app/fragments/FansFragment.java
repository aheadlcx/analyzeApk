package qsbk.app.fragments;

import android.os.Bundle;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class FansFragment extends QiuYouFragment {
    public FansFragment() {
        super(Constants.REL_GET_FANS, FansFragment.class.getSimpleName());
    }

    public FansFragment(Bundle bundle) {
        super(Constants.REL_GET_FANS, FansFragment.class.getSimpleName(), bundle);
    }

    protected void i_() {
        this.d.set(UIHelper.getEmptyImg(), getResources().getString(R.string.no_fans_prompt));
        this.d.show();
    }
}
