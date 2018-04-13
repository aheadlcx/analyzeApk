package qsbk.app.im.CollectEmotion;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.R;
import qsbk.app.fragments.BaseFragment;

public class CollectFragment extends BaseFragment {
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.emotion_grid, null);
    }
}
