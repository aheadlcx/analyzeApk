package qsbk.app.fragments;

import android.app.Activity;

public abstract class BrowseBaseFragment extends BaseFragment {
    public MediaClickListener mMediaClickListener;

    public interface MediaClickListener {
        void onMediaClick();
    }

    public abstract boolean isMediaSaved();

    public abstract void saveMedia();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MediaClickListener) {
            this.mMediaClickListener = (MediaClickListener) activity;
            return;
        }
        throw new IllegalArgumentException(activity.getClass().getSimpleName() + " must implement MediaClickListener");
    }
}
