package qsbk.app.im;

class gz implements Runnable {
    final /* synthetic */ IMMessageListFragment a;

    gz(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void run() {
        if (!this.a.isDetached() && this.a.getActivity() != null) {
            this.a.getActivity().runOnUiThread(new ha(this));
        }
    }
}
