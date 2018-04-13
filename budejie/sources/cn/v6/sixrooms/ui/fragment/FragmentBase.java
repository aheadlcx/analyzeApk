package cn.v6.sixrooms.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import cn.v6.sixrooms.utils.ViewUtil;

public class FragmentBase extends Fragment {
    protected void resetViewSize(View[] viewArr, int[][] iArr) {
        ViewUtil.resetViewSize(getActivity(), viewArr, iArr);
    }
}
