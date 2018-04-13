package qsbk.app.fragments;

class cn implements Runnable {
    final /* synthetic */ FollowCircleFragment a;

    cn(FollowCircleFragment followCircleFragment) {
        this.a = followCircleFragment;
    }

    public void run() {
        this.a.h.autoPlay();
    }
}
