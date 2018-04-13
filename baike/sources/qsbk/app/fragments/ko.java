package qsbk.app.fragments;

class ko implements Runnable {
    final /* synthetic */ RandomDoorStartFragment a;

    ko(RandomDoorStartFragment randomDoorStartFragment) {
        this.a = randomDoorStartFragment;
    }

    public void run() {
        this.a.start();
    }
}
