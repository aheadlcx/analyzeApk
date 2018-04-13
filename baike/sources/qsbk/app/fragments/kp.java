package qsbk.app.fragments;

class kp implements Runnable {
    final /* synthetic */ RandomDoorStartFragment a;

    kp(RandomDoorStartFragment randomDoorStartFragment) {
        this.a = randomDoorStartFragment;
    }

    public void run() {
        this.a.stop();
    }
}
