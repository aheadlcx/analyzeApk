package qsbk.app.fragments;

class gm implements Runnable {
    final /* synthetic */ MyProfileFragment a;

    gm(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void run() {
        if (!this.a.isDetached() && this.a.getActivity() != null) {
            this.a.getActivity().runOnUiThread(new gn(this));
        }
    }
}
