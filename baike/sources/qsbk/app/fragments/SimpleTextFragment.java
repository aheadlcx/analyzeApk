package qsbk.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class SimpleTextFragment extends BaseFragment {
    public static final String KEY = "text";

    public static SimpleTextFragment newInstance(String str) {
        SimpleTextFragment simpleTextFragment = new SimpleTextFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", str);
        simpleTextFragment.setArguments(bundle);
        return simpleTextFragment;
    }

    String a(Bundle bundle) {
        if (bundle == null) {
            return "Empty text!";
        }
        return bundle.getString("text");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View textView = new TextView(getActivity());
        textView.setBackgroundColor(getActivity().getResources().getColor(17170443));
        textView.setText(a(getArguments()));
        textView.setGravity(17);
        textView.setLayoutParams(new LayoutParams(-1, -1));
        return textView;
    }
}
