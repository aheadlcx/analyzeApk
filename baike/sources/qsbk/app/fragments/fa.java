package qsbk.app.fragments;

class fa implements Runnable {
    final /* synthetic */ LiveTabsFragment a;

    fa(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void run() {
        if (!this.a.isDetached() && this.a.getActivity() != null) {
            this.a.getActivity().runOnUiThread(new fb(this));
        }
    }
}
