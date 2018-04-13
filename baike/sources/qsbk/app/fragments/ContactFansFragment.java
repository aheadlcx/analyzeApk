package qsbk.app.fragments;

import android.os.Bundle;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class ContactFansFragment extends ContactQiuYouFragment {
    public ContactFansFragment() {
        super(Constants.REL_GET_FANS, ContactFansFragment.class.getSimpleName());
    }

    public ContactFansFragment(Bundle bundle) {
        super(Constants.REL_GET_FANS, ContactFansFragment.class.getSimpleName(), bundle);
    }

    protected void a() {
        this.d.set(UIHelper.getEmptyImg(), getResources().getString(R.string.no_fans_prompt));
        this.d.show();
    }

    protected void b() {
        if (!this.e.contains("&clr_count=1") && this.f == 1) {
            this.e += "&clr_count=1";
        }
        super.b();
    }
}
