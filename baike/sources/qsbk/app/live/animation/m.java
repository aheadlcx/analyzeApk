package qsbk.app.live.animation;

class m implements Runnable {
    final /* synthetic */ ChristmasAnimation a;

    m(ChristmasAnimation christmasAnimation) {
        this.a = christmasAnimation;
    }

    public void run() {
        this.a.b.mUserInfoLayout.setVisibility(4);
    }
}
