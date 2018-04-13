package qsbk.app.utils.image;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class RetainFragment extends Fragment {
    private Object a;

    public static RetainFragment findOrCreateRetainFragment(FragmentManager fragmentManager) {
        RetainFragment retainFragment = (RetainFragment) fragmentManager.findFragmentByTag("RetainFragment");
        if (retainFragment != null) {
            return retainFragment;
        }
        Fragment retainFragment2 = new RetainFragment();
        fragmentManager.beginTransaction().add(retainFragment2, "RetainFragment").commit();
        return retainFragment2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public Object getObject() {
        return this.a;
    }

    public void setObject(Object obj) {
        this.a = obj;
    }
}
